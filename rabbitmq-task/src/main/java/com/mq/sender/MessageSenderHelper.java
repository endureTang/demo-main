package com.mq.sender;

import com.model.rabbitMq.QueueConfig;
import com.mq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class MessageSenderHelper {

    @Resource
    private RabbitConfig rabbitConfig;
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param mqPropertiesIndex mq 消息队列下标
     * @param jsonStrMsg 消息内容，json格式
     */
    public void sendJsonMQ(Integer mqPropertiesIndex,String jsonStrMsg){
        QueueConfig queueConfig = rabbitConfig.getQueue().get(mqPropertiesIndex);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(queueConfig.getExchange(),queueConfig.getKey(), jsonStrMsg,correlationId);
    }
}
