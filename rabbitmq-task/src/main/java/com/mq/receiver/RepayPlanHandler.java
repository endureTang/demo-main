package com.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
@Component
public class RepayPlanHandler implements ChannelAwareMessageListener {
    // 日志
    private Logger logger = LoggerFactory.getLogger(RepayPlanHandler.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

    }

}
