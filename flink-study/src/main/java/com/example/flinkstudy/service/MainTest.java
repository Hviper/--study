package com.example.flinkstudy.service;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class MainTest {
    public static void main(String[] args) throws Exception {

        // 设置 Flink 执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> stringDataStreamSource = env.readTextFile("input/read.txt");
        SingleOutputStreamOperator<Tuple2<String, Integer>> tuple = stringDataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] s1 = s.split(" ");
                for (String string : s1) {
                    collector.collect(Tuple2.of(string, 1));
                }
            }
        });
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = tuple.keyBy(0).sum(1);
        sum.print();


        // 执行 Flink 作业
        env.execute("Spring Boot Flink Example Job");
    }
}
