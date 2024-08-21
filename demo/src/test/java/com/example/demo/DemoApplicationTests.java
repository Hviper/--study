package com.example.demo;

import com.example.demo.entity.SysConfig;
import com.example.demo.service.ISysConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ISysConfigService service;
    @Test
    void contextLoads() {
        SysConfig one = service.getOne(null);
        System.out.println(one);
    }

}
