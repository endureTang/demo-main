package com.mq.receiver;

import com.alibaba.fastjson.JSONObject;
import com.feign.ApiServiceFeign;
import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderHandler implements ChannelAwareMessageListener {
	private final static Logger logger = LoggerFactory.getLogger(OrderHandler.class);

	@Resource
	private ApiServiceFeign apiServiceFeign;
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			//一次最多处理消息条数
			channel.basicQos(1);
			String msg = new String(message.getBody(), "utf-8");
			logger.info("MqMsgHandler接收到消息："+ msg);
			User user = JSONObject.parseObject(msg, User.class);
			ResponseMsgDto responseMsgDto = apiServiceFeign.spikeSkill(user);
			if (responseMsgDto.getResultStatus()==ResponseMsgDto.SUCCESS) {
				channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
				logger.info("MqMsgHandler消息处理完毕");
			} else {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);//消息处理失败，重回队列
				logger.info("保存用户失败，错误信息:{}",responseMsgDto.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);//消息处理失败，重回队列
			throw new Exception("消息处理错误"+e);
		}

	}
}
