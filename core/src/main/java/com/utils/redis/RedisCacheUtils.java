package com.utils.redis;

import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class RedisCacheUtils {
    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:39
     *  @Description: 根据redis和key获取value，如果为空，则初始化为0
     */ 
    public static String getAndInitRedisValueByKey(RedisLock redisLock, String key,Integer existSecond){
        if (redisLock == null || StringUtils.isEmpty(key)){
            return null;
        }
        String value = redisLock.getRedisTemplate().opsForValue().get(key);
        if(StringUtils.isEmpty(value)){
            value = "0";
            redisLock.getRedisTemplate().opsForValue().set(key,value,existSecond, TimeUnit.SECONDS);
        }
        return  value;
    }
    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:39
     *  @Description: 根据redis和key获取value
     */
    public static String getValueByKey(RedisLock redisLock, String key){
        if (redisLock == null || StringUtils.isEmpty(key)){
            return null;
        }
        String value = redisLock.getRedisTemplate().opsForValue().get(key);
        return  value;
    }
    /**
     *  @author: endure
     *  @Date: 2020/1/10 20:39
     *  @Description: 根据redis和key设置value
     */
    public static boolean setValueByKey(RedisLock redisLock, String key,String value){
        if (redisLock == null || StringUtils.isEmpty(key)){
            return false;
        }
        redisLock.getRedisTemplate().opsForValue().set(key,value,60*3, TimeUnit.SECONDS);
        return true;
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
