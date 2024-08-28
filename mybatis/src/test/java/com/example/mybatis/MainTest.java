package com.example.mybatis;


import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
/*
 * springboot
 * */

public class MainTest {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        System.out.println();
        System.out.println();
        HashMap<String,String> map = new HashMap<>();
        map.put("kiwi", "value");
        System.out.println();

        System.out.println();
        System.out.println();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });

    }
}
