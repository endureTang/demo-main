package com.api;

import com.model.exception.CommonException;
import com.model.generate.Orderinfo;
import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import com.service.OrderService;
import com.utils.redis.RedisLock;
import com.utils.string.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description: 订单接口类
 * @author: endure
 * @create: 2020-01-10 11:06
 **/
@RestController
@RequestMapping(value = "orderApi")
public class OrderApi {
    private static Logger logger = LoggerFactory.getLogger(OrderApi.class);
    @Resource
    private OrderService orderService;
    @Resource
    private RedisLock redisLock; //注入redis加锁服务
    /** 
    * @Description: 秒杀商品
    * @Param:
    * @return:
    * @Author: endure
    * @Date: 2020/1/10 
    */
    @RequestMapping(value = "spikeSkill")
    public ResponseMsgDto spikeSkill(@RequestBody User user){
        ResponseMsgDto responseMsgDto = new ResponseMsgDto(ResponseMsgDto.SUCCESS);
        String redisKey = "spikeSkill";//秒杀加锁key
        String zuulRateLimitKey = "product_1";//zuul限流缓存key

        String token = null; //加锁成功token
        try {
            Random random = new Random();
            Integer id = random.nextInt(10000); //模拟多个用户下单
            user.setId(id.toString());
            Orderinfo order = new Orderinfo();
            order.setUserid(user.getId());
            order.setProductid("1");
            order.setCreatedate(new Date());
            order.setStatus(0);
            order.setId(UuidUtil.get32UUID());
            //创建分布式锁
            token = redisLock.lock(redisKey, 10000, 11000);
            if(token != null) {
                logger.info(Thread.currentThread().getName() + "获取了锁");
                String value = redisLock.getRedisTemplate().opsForValue().get(zuulRateLimitKey);
                if(StringUtils.isEmpty(value)){
                    redisLock.getRedisTemplate().opsForValue().set(zuulRateLimitKey,"1",60*60, TimeUnit.SECONDS);
                }else{
                    redisLock.getRedisTemplate().boundValueOps(zuulRateLimitKey).increment(1);//zuul限流 +1
                }
                orderService.spikeOrder(order);
            }else{
                logger.info("获取锁超时");
            }
        }catch (CommonException e){
            logger.error("业务异常{}", e.getMessage());
            responseMsgDto.setResultStatus(ResponseMsgDto.SUCCESS);
            responseMsgDto.setMsg(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误{}", e);
            responseMsgDto.setResultStatus(ResponseMsgDto.FAIL);
            responseMsgDto.setMsg("系统异常");
        }finally {
            redisLock.unlock(redisKey, token);
            logger.info(Thread.currentThread().getName() + "释放了锁");
            redisLock.getRedisTemplate().boundValueOps(zuulRateLimitKey).increment(-1);//zuul限流 +1
        }
        return  responseMsgDto;
    }
}
