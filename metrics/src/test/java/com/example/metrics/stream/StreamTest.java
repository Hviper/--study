package com.example.metrics.stream;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        boolean b = list.stream().anyMatch(item -> item.equals("3"));
        System.out.println(b);
    }
}
