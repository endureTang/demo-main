package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "redis",ignoreUnknownFields = false)
@PropertySource("classpath:test/zuulConfig.properties")
@Component
public class ZuulConfig {
    //商品秒杀限流key
    private String zuulRateLimitKey;
    //商品秒杀限流阈值
    private String rateLimitAmount;

    public String getRateLimitAmount() {
        return rateLimitAmount;
    }

    public void setRateLimitAmount(String rateLimitAmount) {
        this.rateLimitAmount = rateLimitAmount;
    }

    public String getZuulRateLimitKey() {
        return zuulRateLimitKey;
}
    public void setZuulRateLimitKey(String zuulRateLimitKey) {
        this.zuulRateLimitKey = zuulRateLimitKey;
    }
}
