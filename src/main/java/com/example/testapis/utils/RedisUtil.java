package com.example.testapis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public String get(final  String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key,String value){
        boolean result=false;
        try{
            redisTemplate.opsForValue().set(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }

    public boolean getAndSet(final String key,String value){
        boolean result=false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
    public boolean count(final String key,String value){
        boolean result=false;
        try {
            result= value.equals(redisTemplate.opsForValue().get(key));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key){
        boolean result=false;
        try{
            redisTemplate.delete(key);
            result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
}
