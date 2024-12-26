package org.example.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class ControllerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ControllerApplication.class, args);
        RedisTemplate bean = run.getBean("redisTemplate", RedisTemplate.class);
        System.out.println("hello");
        System.out.println();

    }

}
