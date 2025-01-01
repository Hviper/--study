package com.example.redisstarter.test;

import java.util.HashMap;

public class TestMain {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        Object o = map.computeIfAbsent("key2", k -> "value2");
        System.out.println(o);
        Object o1 = map.computeIfPresent("key1", (k, v) -> v + "1");
        System.out.println(o1);
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
    }
}
