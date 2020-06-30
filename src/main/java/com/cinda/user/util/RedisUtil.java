package com.cinda.user.util;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class RedisUtil {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    public boolean set(String key, Object object){
        String json = JSON.toJSONString(object);
        try{
            redisTemplate.opsForValue().set(key,json, Duration.ofMinutes(30));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean set(String key, Object object, int minutes){
        String json = JSON.toJSONString(object);
        try{
            redisTemplate.opsForValue().set(key,json, Duration.ofMinutes(minutes));
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
