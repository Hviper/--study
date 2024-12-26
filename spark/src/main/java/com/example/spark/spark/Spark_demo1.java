package com.example.spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Spark_demo1 {
    public static void main(String[] args) throws InterruptedException {
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


        JavaPairRDD<Integer, Integer> mapToPair = parallelize.mapToPair(new PairFunction<Integer, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(Integer integer) throws Exception {
                System.out.println("-------");
                return new Tuple2<>(integer%3, integer);
            }
        });


        mapToPair.collect();
        System.out.println("000000000000000000000000000000");
        mapToPair.collect();






        JavaPairRDD<Integer, Integer> reduceByKey = mapToPair.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return 0;
            }
        });

        System.out.println(reduceByKey.rdd().getDependencies());

        reduceByKey.collect();

        System.out.println("-------------------------------------------");

        reduceByKey.collect();

        System.out.println(reduceByKey.rdd().getDependencies());






        sc.close();

    }
}
