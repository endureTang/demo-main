package com.mq.receiver;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class RepayPlanHandler implements ChannelAwareMessageListener {
	private final static Logger logger = LoggerFactory.getLogger(RepayPlanHandler.class);

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		//一次最多处理消息条数
		channel.basicQos(1);
		String msg = new String(message.getBody(), "utf-8");
		logger.info("MqMsgHandler接收到消息："+ msg);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
		logger.info("MqMsgHandler消息处理完毕");
	}
}
