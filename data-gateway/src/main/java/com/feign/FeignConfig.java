package com.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * 自定义feign配置
 */
@Configuration
public class FeignConfig {
	/**
	 * 设置feign调用日志等级
	 * @return
	 */
	@Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
	}
}
