/**
 * 
 */
package com;

import com.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

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
@RibbonClient(name = "task",configuration = RibbonConfiguration.class)
public class MqTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(MqTaskApplication.class, args);
	}
}
