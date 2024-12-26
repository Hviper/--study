package org.example.controller;


import org.example.controller.service.UserRedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class ControllerApplicationTests {
    @Autowired
    private UserRedisService userRedisService;

    @Test
    public void testRedisOperations() {
        // 保存用户实例
        HashMap<String, String> map = new HashMap<>();
        map.put("111", "111");
        map.put("111", "111");
        map.put("111", "111");

    }
}
