package com.example.spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class SparkShuffle {
    public static void main(String[] args) {
        // 创建一个 Spark 配置和上下文管理对象
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        JavaRDD<String> parallelize = sc.parallelize(list);
        parallelize.groupBy(new Function<String, Integer>() {
            @Override
            public Integer call(String v1) throws Exception {
                return 0;
            }
        });

        parallelize.foreach(System.out::println);

        JavaPairRDD<String, Integer> pairRDD = parallelize.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });
        pairRDD.collect().forEach(System.out::println);


        // 关闭 Spark 上下文
        sc.close();
    }
}
