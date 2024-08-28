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
                .databaseList("mydb")
                .tableList("mydb.user")
                .username("root")
                .password("123456")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .includeSchemaChanges(true)
                .build();
//        启动webui，绑定本地web-ui端口号
        Configuration configuration=new Configuration();
        configuration.setInteger(RestOptions.PORT,8081);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);

        env.enableCheckpointing(5000);
        env.fromSource(source, WatermarkStrategy.noWatermarks(),"MYSQL Source")
                .addSink(new CustomSink());
        env.execute();
    }

}
