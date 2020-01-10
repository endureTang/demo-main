package com.utils.redis;

import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class RedisCacheUtils {
    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:39
     *  @Description: 根据redis和key获取value
     */ 
    public static String getRedisValueByKey(RedisLock redisLock, String key){
        if (redisLock == null || StringUtils.isEmpty(key)){
            return null;
        }
        String value = redisLock.getRedisTemplate().opsForValue().get(key);
        if(StringUtils.isEmpty(value)){
            value = "0";
            redisLock.getRedisTemplate().opsForValue().set(key,"0",60*60, TimeUnit.SECONDS);
        }
        return  value;
    }

    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:56
     *  @Description: 加一
     */
    public static void increment(RedisLock redisLock,String key){
        redisLock.getRedisTemplate().boundValueOps(key).increment(1);
    }
    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:56
     *  @Description: 减一
     */
    public static void deIncrement(RedisLock redisLock,String key){
        redisLock.getRedisTemplate().boundValueOps(key).increment(-1);
    }
}
