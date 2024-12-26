package com.example.spark.spark.sparksql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions;

import static org.apache.spark.sql.types.DataTypes.StringType;

public class SparkSql02_DSL {

    public static void main(String[] args) {
        // 创建 SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("Read Txt as JSON")
                .master("local[*]")  // 本地模式
                .getOrCreate();

        spark.udf().register("customer", (UDF1<String, String>) o -> "null" + o,StringType);



        // 读取 txt 文件，每一行作为字符串
        Dataset<Row> json = spark.read().json("input/json.json");
        json.select("duty_team", "maintainer").show();


        spark.close();

    }
}
