package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "redis",ignoreUnknownFields = false)
@PropertySource(value = {"classpath:${spring.profiles.active}/redis.properties"},encoding = "utf-8")
public class RedisKeyConfig {
    private String zuulRateLimitKey;
    private String stockKey;
    private String stockAmount;

    public String getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(String stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getStockKey() {
        return stockKey;
    }

    public void setStockKey(String stockKey) {
        this.stockKey = stockKey;
    }

    public String getZuulRateLimitKey() {
        return zuulRateLimitKey;
    }

    public void setZuulRateLimitKey(String zuulRateLimitKey) {
        this.zuulRateLimitKey = zuulRateLimitKey;
    }
}
