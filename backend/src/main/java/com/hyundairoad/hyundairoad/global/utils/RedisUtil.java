//package com.hyundairoad.hyundairoad.global.utils;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//
//@Service
//@RequiredArgsConstructor
//public class RedisUtil {
//    private final StringRedisTemplate redisTemplate;
//
//    public String getData(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public void setData(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    public void setDataExpire(String key, String value, long duration) {
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        Duration expireDuration = Duration.ofSeconds(duration);
//        valueOperations.set(key, value, expireDuration);
//    }
//
//    public void deleteData(String key) {
//        redisTemplate.delete(key);
//    }
//}
