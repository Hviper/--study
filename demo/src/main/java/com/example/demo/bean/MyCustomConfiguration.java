package com.example.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCustomConfiguration {
    @Bean
    public HHH myCustomBean() {
        return new HHH();
    }
}
