package com.example.redisstarter.service;

import com.example.redisstarter.bean.Cat;
import com.example.redisstarter.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRedisService {
    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;

    @Autowired
    private RedisTemplate<String, Cat> catRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void test(){
        User user = new User("zhuye", "36","123456");

        userRedisTemplate.opsForValue().set(user.getName(), user);

        User userFromRedis = userRedisTemplate.opsForValue().get(user.getName());

        log.info("userRedisTemplate get {} {}", userFromRedis, userFromRedis.getClass());
        log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get(user.getName()));
        log.info("catRedisTemplate get {}", catRedisTemplate.opsForValue().get(user.getName()));
    }

}
