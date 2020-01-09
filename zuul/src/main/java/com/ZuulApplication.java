package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: zuul启动类
 * @author: endure
 * @create: 2020-01-06 10:37
 **/

@SpringBootApplication
@EnableDiscoveryClient//eureka服务发现注册
@EnableZuulProxy //开启zuul网关路由
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
