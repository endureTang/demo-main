package com.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.utils.redis.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 订单秒杀限流过滤器
 * @author: endure
 * @create: 2020-01-10 10:34
 **/
@Component
public class OrderRateLimitFilter extends ZuulFilter {
    @Resource
    private RedisLock redisLock; //注入redis加锁服务
    private final static Logger logger = LoggerFactory.getLogger(OrderRateLimitFilter.class);
    @Override
    public String filterType() {
        //路由前过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤顺序，越小越靠前
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        //过滤规则
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(request.getRequestURI().startsWith("/zuulTask/mqSendApi/sendOrderMq")){
            return true;
        }
        if(request.getRequestURI().startsWith("/zuulApi/orderApi/spikeSkill")){
            return true;
        }
        return false;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        String value = redisLock.getRedisTemplate().opsForValue().get("product_1");
        try {
            logger.info( "参与抢购的人数：" + value);
            if(Integer.parseInt(value)> 1000)                {
                String msg="参与抢购的人太多，请稍后再试一试";
                errorHandle(ctx, msg);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg="计数异常";
            errorHandle(ctx, msg);
            return null;
        }
        return null;
    }

    /**
     * 统一的异常拦截
     */

    private void errorHandle(RequestContext ctx, String msg) {
        ctx.setSendZuulResponse(false);
        try {
            logger.info(msg);
            ctx.getResponse().setContentType("application/json;charset=utf-8");
            ctx.getResponse().getWriter().write(msg);
        } catch (Exception e) {
            logger.error("zuul拦截"+e);
            e.printStackTrace();
        }
    }
}
