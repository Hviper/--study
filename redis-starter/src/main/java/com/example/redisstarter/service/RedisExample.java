package com.example.redisstarter.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisExample {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKeyValue(String key, String value) {
        // 使用 StringRedisTemplate 存储字符串类型的数据
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getKeyValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setObject(Object key, Object object) {
        // 使用 RedisTemplate 存储对象
        redisTemplate.opsForValue().set(key, object);
    }

    public Object getObject(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
}

