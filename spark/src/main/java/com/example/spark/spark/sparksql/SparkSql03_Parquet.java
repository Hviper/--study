package com.example.spark.spark.sparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql03_Parquet {
    public static void main(String[] args) {
        // 创建 SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("Read Txt as JSON")
                .master("local[*]")  // 本地模式
                .getOrCreate();

        // 读取 txt 文件，每一行作为字符串
        Dataset<Row> json = spark.read().parquet("input/json.parquet");
        json.show();

        spark.close();

    }
}
