package com.example.redisstarter;

import com.example.redisstarter.service.CacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@EnableCaching
@SpringBootApplication
public class RedisStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisStarterApplication.class, args);
        CacheService service = run.getBean(CacheService.class);
        String query1 = service.query(999);
        String query2 = service.query(999);
        CacheManager cacheManager = run.getBean(CacheManager.class);


        cacheManager.getCacheNames().forEach(System.out::println);
        System.out.println(cacheManager);
    }

}
