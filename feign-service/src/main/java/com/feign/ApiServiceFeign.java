package com.feign;

import com.feign.fallBack.ApiServiceFallBack;
import com.model.generate.User;
import com.model.responseDto.ResponseMsgDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="api",configuration=FeignConfig.class,fallback = ApiServiceFallBack.class)
public interface ApiServiceFeign {

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping(value="/user/addUser")
	ResponseMsgDto addUser(@RequestBody User user);

	/**
	 * 秒杀商品
	 * @param user
	 * @return
	 */
	@PostMapping(value="/orderApi/spikeSkill")
	ResponseMsgDto spikeSkill(@RequestBody User user);
}
