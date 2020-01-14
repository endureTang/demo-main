package com.cfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "redis",ignoreUnknownFields = false)
@PropertySource(value = {"classpath:${spring.profiles.active}/redis.properties"},encoding = "utf-8")
public class RedisKeyConfig {
    private String redisSpikeSkillKey;
    private String zuulRateLimitKey;
    private String lockExpireTime;
    private String lockTimeout;
    private String stockKey;


    public String getRedisSpikeSkillKey() {
        return redisSpikeSkillKey;
    }

    public void setRedisSpikeSkillKey(String redisSpikeSkillKey) {
        this.redisSpikeSkillKey = redisSpikeSkillKey;
    }

    public String getZuulRateLimitKey() {
        return zuulRateLimitKey;
    }

    public void setZuulRateLimitKey(String zuulRateLimitKey) {
        this.zuulRateLimitKey = zuulRateLimitKey;
    }

    public String getLockExpireTime() {
        return lockExpireTime;
    }

    public void setLockExpireTime(String lockExpireTime) {
        this.lockExpireTime = lockExpireTime;
    }

    public String getLockTimeout() {
        return lockTimeout;
    }

    public void setLockTimeout(String lockTimeout) {
        this.lockTimeout = lockTimeout;
    }

    public String getStockKey() {
        return stockKey;
    }

    public void setStockKey(String stockKey) {
        this.stockKey = stockKey;
    }
}
