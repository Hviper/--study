package com.example.flinkcdc.test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        LockTemplate<?> lockTemplate = new LockTemplate<>();
        Object lock = lockTemplate.lock((Callable) () -> {
            Map<String,String> map = new HashMap<>();
            map.put("key1", "value1");
            map.put("key2", "value2");

            System.out.println("远程调用");
            return map;
        });
        System.out.println(lock);
    }

    static class LockTemplate<T>{
        public T lock(Callable<T> callable) throws Exception {
            System.out.println("--------------");
            T call = callable.call();
            System.out.println("--------------");
            return call;
        }
    }



}
