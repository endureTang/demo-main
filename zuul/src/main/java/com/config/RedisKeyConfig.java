package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "redis",ignoreUnknownFields = false)
@PropertySource("classpath:test/redisKeyConfig.properties")
@Component
public class RedisKeyConfig {
    private String zuulRateLimitKey;

    public String getZuulRateLimitKey() {
        System.out.println("====================zuulRateLimitKey=================="+zuulRateLimitKey);
        return zuulRateLimitKey;
    }

    public void setZuulRateLimitKey(String zuulRateLimitKey) {
        System.out.println("====================zuulRateLimitKey=================="+zuulRateLimitKey);
        this.zuulRateLimitKey = zuulRateLimitKey;
    }
}
