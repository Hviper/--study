package com.example.mybatis.controller;

import com.example.mybatis.bean.User;
import com.example.mybatis.kafka.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Enumeration;

@RestController
public class HelloController {
    @Autowired
    KafkaProducer kafkaProducer;
    @GetMapping("hello")
    public String hello() {
        kafkaProducer.sendMessage("my-topic","message arrived.......");
        return "hello";
    }
    //没有加@RequestBody注解默认是冲
    @PostMapping("post")
    public User post(HttpServletResponse response , HttpServletRequest request, User user) {
        System.out.println(request);
        System.out.println(request.getRequestURI());
        System.out.println(response);
        return user;
    }
}
