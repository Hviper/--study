package com.mybatisdemo.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }
    @PreAuthorize("@ss.hasPermi('system:user:test')")
    @GetMapping("hello2")
    public String hello2() {
        return "Hello World";
    }
    @PreAuthorize("@ss.hasPermi('system:user:xx')")
    @GetMapping("hello3")
    public String hello3() {
        return "Hello World";
    }
}

