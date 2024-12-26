package com.example.spark.spark.sparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class SparkSql03_JDBC {
    public static void main(String[] args) {
        // 创建 SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("Read Txt as JSON")
                .master("local[*]")  // 本地模式
                .getOrCreate();

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");

        // 读取 txt 文件，每一行作为字符串
        Dataset<Row> jdbc = spark.read().jdbc("jdbc:mysql://localhost:3306/flink-es?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8",
                "data_entity",properties);

        jdbc.show();

        jdbc.write().json("output/json");

        spark.close();

    }
}
