package com.feign;

import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="rabbitmq-task",configuration=FeignConfig.class)
public interface MqOrderService {

	/**
	 * 订单消息推送
	 * @param user
	 * @return
	 */
	@PostMapping(value="/mqSendApi/sendOrderMq")
	ResponseMsgDto sendOrderMq(@RequestBody User user);


}
