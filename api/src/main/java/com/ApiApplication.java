/**
 * 
 */
package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @Name com.ApiApplication
 * @Description
 * @Author trj
 * @Version 2019年12月10日上午10:31:49
 * @Copyright self
 *
 */
@SpringBootApplication
@MapperScan("com.mapper")
@EnableDiscoveryClient//eureka服务发现注册
@EnableFeignClients // 开启Feign
@EnableCaching
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
