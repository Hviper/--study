package com.example.mybatis;

import com.example.mybatis.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisApplicationTests {
    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    void contextLoads() {
        kafkaProducer.sendMessage("my-topic","hello，world kiwi successful！！！！");
        while (true);
    }



}
