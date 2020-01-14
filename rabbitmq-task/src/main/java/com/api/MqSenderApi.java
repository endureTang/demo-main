package com.api;

import com.alibaba.fastjson.JSONObject;
import com.config.RedisKeyConfig;
import com.model.generate.User;
import com.model.rabbitMq.QueueListIndex;
import com.mq.sender.MessageSenderHelper;
import com.utils.redis.RedisCacheUtils;
import com.utils.redis.RedisLock;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
    @RequestMapping(value = "mqSendApi")
public class MqSenderApi {
    private static Logger logger = LoggerFactory.getLogger(MqSenderApi.class);
    @Resource
    private MessageSenderHelper messageSenderHelper;
    @Resource
    private RedisLock redisLock; //注入redis加锁服务
    @Resource
    private RedisKeyConfig redisKeyConfig;

    /** 
    * @Description: 订单消息发送
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2019/12/31 
    */
    @RequestMapping(value = "sendOrderMq")
    public String sendOrderMq(@RequestBody User user){
        String stockKey = redisKeyConfig.getStockKey();
        String stockAmount = redisKeyConfig.getStockAmount();
        try {
            Random random = new Random();
            Integer id = random.nextInt(10000); //模拟多个用户下单
            user.setId(id.toString());
            String userStr = JSONObject.toJSONString(user);
            String stockValue = RedisCacheUtils.getValueByKey(redisLock,stockKey);
            logger.info("已下单数量：{}",stockValue);
            if(StringUtils.isNotEmpty(stockValue) && Integer.parseInt(stockValue) >= Integer.parseInt(stockAmount)){
                logger.info("卖完啦");
                return "卖完啦！";
            }
            messageSenderHelper.sendJsonMQ(QueueListIndex.ORDER_QUEUE, userStr);
            RedisCacheUtils.increment(redisLock,redisKeyConfig.getZuulRateLimitKey());
            return  "消息发送成功";

        } catch (Exception e){
            logger.error("系统错误"+e);
            return  "消息发送失败";
        }
    }
}
