package com.example.spark.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.ArrayList;
import java.util.List;

public class SparkSubmitDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建 Spark 配置和上下文管理对象
        SparkConf conf = new SparkConf().setAppName("DriverExecutorLogExample").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 2. 创建数据并并行化
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");

        JavaRDD<String> parallelize = sc.parallelize(list);

        // 3. 在 Driver 中打印日志
        System.out.println("这是Driver中执行的日志");

        // 4. 使用 foreach 操作遍历并执行任务，打印 Executor 日志
        parallelize.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("这是Executor中执行的日志，打印数据：" + s);
            }
        });

        // 5. 使用 map 操作进行转换，打印 Executor 日志
        JavaRDD<String> map = parallelize.map(new Function<String, String>() {
            @Override
            public String call(String v1) throws Exception {
                System.out.println("这是Executor中执行的日志，打印变换的数据：" + v1);
                return v1 + "<----->";
            }
        });

        // 6. 执行 collect，触发执行并打印 Executor 日志
        List<String> collect = map.collect();

        // 7. 打印最终结果（在 Driver 上执行）
        System.out.println("这是Driver中收集的结果：" + collect);


        Thread.sleep(1000000);

        // 8. 关闭 Spark 上下文
        sc.close();
    }
}
