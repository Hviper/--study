package com.example.flinkcdc.flink;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.source.MySqlSourceBuilder;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkMain {
    public static void main(String[] args) throws Exception {
        MySqlSourceBuilder<String> builder = MySqlSource.builder();
        MySqlSource<String> source = builder.hostname("127.0.0.1")
                .port(3306)
                .databaseList("flink-es")
                .tableList("flink-es.article")
                .username("root")
                .password("123456")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .includeSchemaChanges(true)
                .build();
//        启动webui，绑定本地web-ui端口号
        Configuration configuration=new Configuration();
        configuration.setString("state.checkpoints.dir", "file:///Users/aidis/IdeaProjects/--study/flink-CDC/src/main/resources/flink_point");  // 设置Checkpoint存储路径
        configuration.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);

        env.enableCheckpointing(5000);

//        // 从 MySQL 读取数据并转化为数据流
//        DataStream<String> mysqlStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "MySQL Source");
//
//        // 设置 Kafka 生产者（Sink）
//        String kafkaTopic = "my_kafka_topic";  // 目标 Kafka Topic
//        String kafkaBootstrapServers = "localhost:9092";  // Kafka 服务器地址
//
//        FlinkKafkaProducer<String> kafkaSink = new FlinkKafkaProducer<>(
//                kafkaBootstrapServers,              // Kafka 集群地址
//                kafkaTopic,                         // Kafka Topic
//                new SimpleStringSchema()            // 数据序列化方式
//        );
//
//        // 将数据流写入 Kafka
//        mysqlStream.addSink(kafkaSink);

        env.fromSource(source, WatermarkStrategy.noWatermarks(),"MYSQL Source")
                .addSink(new CustomSink());
        env.execute();
    }

}
