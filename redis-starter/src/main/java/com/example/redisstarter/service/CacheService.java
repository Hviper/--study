package com.example.redisstarter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @CacheEvict(value = "testCache", cacheResolver = "customCacheResolver", key = "#id")
    public String save(Integer id) {
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        return "success";
    }



    @Cacheable(value = "name1",  key = "#id")
    @CacheEvict(value = "name1",  key = "#id", beforeInvocation = true)
    public String query(Integer id) {
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        return "success";
    }


    @Cacheable(value = "name2",  key = "#id")
    public String query2(Integer id) {
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        return "query2 success";
    }



}
