package com.example.redisstarter;

import com.example.redisstarter.service.RedisExample;
import com.example.redisstarter.service.UserRedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class RedisStarterApplicationTests {
    @Autowired
    RedisExample redisExample;
    @Autowired
    UserRedisService userRedisService;

    @Test
    void contextLoads() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("key","value");
        map.put("key2","value2");
        redisExample.setKeyValue("key1", "123");
        redisExample.setKeyValue("key2", "value2");
        String key1 = redisExample.getKeyValue("key1");
        redisExample.setObject(map,map);
        Object object = redisExample.getObject(map);
        System.out.println(key1);
        System.out.println(object);
    }

    @Test
    void contextLoads2() {
        userRedisService.test();
    }

}
