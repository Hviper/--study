package com.nio;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.*;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCountJob {

    public static void main(String[] args) throws Exception {
        /**
         * 实现步骤：
         * 1）初始化flink批处理的运行环境
         * 2）指定文件路径，获取文件数据
         * 3）对获取到的数据进行空格拆分
         * 4）对拆分的单词进行计数，每个单词记一次数
         * 5）对相同的单词进行分组操作
         * 6）对分组后的数据进行累加操作
         * 7）打印输出（测试）
         * 8）启动作业，递交任务
         */
        //初始化flink批处理的运行环境（获取到当前环境，如果本地运行获取local环境）
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //指定文件路径，获取文件数据
        final DataSource<String> lines = env.readTextFile("./data/input/wordcount.txt");

        //对获取到的数据进行空格拆分
        //map与flatmap的区别
        //String：传入值类型
        //String：返回值类型
        /**
         * hello world
         * hello flink
         * hello scala
         * hello spark
         */
        final FlatMapOperator<String, String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String line, Collector<String> out) throws Exception {
                //将每行字符串进行空格拆分
                final String[] dataArray = line.split(" ");
                //循环遍历字符串数组
                for (String word : dataArray) {
                    //需要使用out进行返回数据
                    out.collect(word);
                }
            }
        });

        /**
         * 对拆分的单词进行计数，每个单词记一次数
         * String：传入值类型
         * Tuple2<String, Integer>：返回值类型<单词, 单词次数>
         * 元祖对象最长可以传递25个参数，Tuple1 -> Tuple25， TupleN->N表示参数的个数
         */
        final MapOperator<String, Tuple2<String, Integer>> wordAndOne = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return Tuple2.of(value, 1);
            }
        });

        //对相同的单词进行分组操作
        final UnsortedGrouping<Tuple2<String, Integer>> grouped = wordAndOne.groupBy(0);

        //对分组后的数据进行累加操作
        final AggregateOperator<Tuple2<String, Integer>> summed = grouped.sum(1);

        //打印输出（测试）
        summed.print();

        //todo 8）启动作业，递交任务
        //在批处理开发中以下方法会触发作业的递交操作，故无需 env.execute()
        //'execute()', 'count()', 'collect()', or 'print()'.
        //env.execute();
    }
}
