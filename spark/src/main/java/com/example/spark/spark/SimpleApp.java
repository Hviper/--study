package com.example.spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class SimpleApp {
    public static void main(String[] args) {
        // 创建一个 Spark 配置和上下文管理对象
        SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 读取数据，这里我们直接从一个文本文件读取
        // 你可以替换成任何包含文本数据的文件路径
        String inputFile = "data.txt";
        JavaRDD<String> input = sc.textFile(inputFile,16);



        // 将文本数据分割成单词
        JavaRDD<String> words = input.flatMap(s -> Arrays.asList(s.split(" ")).iterator());

        // 对每个单词进行计数
        JavaPairRDD<String, Integer> counts = words.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum);

        // 将计算结果保存到文件中
        counts.saveAsTextFile("output1");

        // 关闭 Spark 上下文
        sc.close();
    }
}
