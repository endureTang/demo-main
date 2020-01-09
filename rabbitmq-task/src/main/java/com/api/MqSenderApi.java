package com.api;

import com.alibaba.fastjson.JSONObject;
import com.model.generate.User;
import com.model.rabbitMq.QueueListIndex;
import com.mq.sender.MessageSenderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
    @RequestMapping(value = "mqSendApi")
public class MqSenderApi {
    private static Logger logger = LoggerFactory.getLogger(MqSenderApi.class);
    @Resource
    private MessageSenderHelper messageSenderHelper;

    /** 
    * @Description: 订单消息发送
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2019/12/31 
    */
    @RequestMapping(value = "sendOrderMq")
    public String sendOrderMq(@RequestBody User user){
        try {
            String userStr = JSONObject.toJSONString(user);
            logger.info("接收到消息"+userStr);
            messageSenderHelper.sendJsonMQ(QueueListIndex.ORDER_QUEUE, userStr);
            return  "消息发送成功";
        } catch (Exception e){
            logger.error("系统错误"+e);
            return  "消息发送失败";
        }
    }

    @RequestMapping(value = "testZuul")
    public String testZuul(){
        logger.info("zuul访问成功");
        return "访问成功";
    }
}
