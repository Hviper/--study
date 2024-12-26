package com.example.spark.spark.sparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql01_ {
    public static void main(String[] args) throws InterruptedException {
        // 创建 SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("Read Txt as JSON")
                .master("local[*]")  // 本地模式
                .getOrCreate();

        // 读取 txt 文件，每一行作为字符串
        Dataset<Row> json = spark.read().json("input/json.json");
        json.createOrReplaceTempView("json");


        spark.sql("select j1.maintainer from json as j1 join json as j2  group by j1.maintainer").explain("extended");
//        spark.sql("select j1.maintainer from json as j1 join json as j2  group by j1.maintainer").explain("extended");
        Thread.sleep(100000000);
        spark.close();

    }
}
