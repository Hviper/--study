package org.example.controller.service;
// UserRedisService类

import org.example.controller.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRedisService {
    private final RedisTemplate redisTemplate;
    private final String hashKey = "users";

    @Autowired
    public UserRedisService(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveUser(User user) {
        HashOperations<String, String, User> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(hashKey, user.getId(), user);
    }

    public User getUserById(String id) {
        HashOperations<String, String, User> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(hashKey, id);
    }

    public Map<String, User> getAllUsers() {
        HashOperations<String, String, User> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(hashKey);
    }
}
