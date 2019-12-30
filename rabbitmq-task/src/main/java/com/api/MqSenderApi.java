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
     * 订单消息发送
     * @return
     */
    @RequestMapping(value = "sendOrderMq")
    public void sendOrderMq(@RequestBody User user){
        try {
            messageSenderHelper.sendJsonMQ(QueueListIndex.ORDER_QUEUE, JSONObject.toJSONString(user));
        } catch (Exception e){
            logger.error("系统错误"+e);
        }
    }

}
