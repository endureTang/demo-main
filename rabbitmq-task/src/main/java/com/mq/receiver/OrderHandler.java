package com.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OrderHandler implements ChannelAwareMessageListener {
	private final static Logger logger = LoggerFactory.getLogger(OrderHandler.class);

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {

	}
}
