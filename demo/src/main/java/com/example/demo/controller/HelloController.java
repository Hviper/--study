package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000000);
        return "Hello ";
    }

    @GetMapping("/hello2")
    public String sayHello2() {
        return "Hello2 ";
    }
}
