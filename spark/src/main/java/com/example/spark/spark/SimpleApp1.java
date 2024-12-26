package com.example.spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleApp1 {
    public static void main(String[] args) {
        // 创建一个 Spark 配置和上下文管理对象
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(7);
        list.add(9);
        list.add(10);

        JavaRDD<Integer> parallelize = sc.parallelize(list,3);

        AtomicReference<Integer> sum = new AtomicReference<>(0);
        parallelize.foreach(i->{
            sum.updateAndGet(v -> v + i);
        });



        parallelize.collect();

        System.out.println(sum.get());

        // 关闭 Spark 上下文
        sc.close();
    }
}
