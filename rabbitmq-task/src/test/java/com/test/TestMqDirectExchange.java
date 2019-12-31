package com.test;


import com.MqTaskApplication;
import com.model.rabbitMq.QueueConfig;
import com.mq.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 测试Mq消息发送
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqTaskApplication.class)
public class TestMqDirectExchange {
    @Resource
    private RabbitConfig rabbitConfig;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendEndureQueueMsg(){
        QueueConfig queueConfig = rabbitConfig.getQueue().get(0);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(queueConfig.getExchange(),queueConfig.getKey(), "测试订单消息",correlationId);
        QueueConfig queueConfig2 = rabbitConfig.getQueue().get(1);
        CorrelationData correlationId2 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(queueConfig2.getExchange(),queueConfig2.getKey(),"还款计划消息",correlationId2);
        System.out.println("发送消息成功");
    }
}
