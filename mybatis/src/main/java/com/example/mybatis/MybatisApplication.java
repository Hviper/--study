package com.example.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MybatisApplication.class, args);
        ReentrantLock lock = new ReentrantLock();
        String s = new String("ss");

        lock.lock();
        System.out.println();
    }
}
