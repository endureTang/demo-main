/**
 * 
 */
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @Name com.ApiApplication
 * @Description
 * 
 * @Author trj
 * @Version 2019年12月10日上午10:31:49
 * @Copyright self
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients // 开启Feign
public class MqTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(MqTaskApplication.class, args);
	}
}
