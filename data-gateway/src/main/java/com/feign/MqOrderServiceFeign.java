package com.feign;

import com.model.generate.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="rabbitmq-task",configuration=FeignConfig.class,fallback = MqOrderServiceFallback.class)
public interface MqOrderServiceFeign {

	/**
	 * 订单消息推送
	 * @param user
	 * @return
	 */
	@PostMapping(value="/mqSendApi/sendOrderMq")
	String sendOrderMq(@RequestBody User user);
}
