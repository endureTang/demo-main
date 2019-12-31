/**
 * 
 */
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
@SpringBootApplication
@EnableDiscoveryClient
public class MqTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(MqTaskApplication.class, args);
	}
}
