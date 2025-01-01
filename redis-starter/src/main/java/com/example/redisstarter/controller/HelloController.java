package com.example.redisstarter.controller;

import com.example.redisstarter.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    CacheService cacheService;
    @GetMapping("/hello")
    public String hello() {
        return cacheService.save(13);
    }

    @GetMapping("/hello2")
    public String hello2() {
        return cacheService.query(123456);
    }
}
