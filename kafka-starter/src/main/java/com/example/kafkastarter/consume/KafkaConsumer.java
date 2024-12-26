package com.example.kafkastarter.consume;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

//    @RetryableTopic(attempts = "${kafka.retry.attempts}",
//            fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC)
    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(ConsumerRecord<String,String> record) {
        log.info(record.toString());
        Student s = objectMapper.convertValue(record.value(), Student.class);
        System.out.println(s);
//        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
//        log.info("[onMessage][线程编号:{} messageHeaders：{}]", Thread.currentThread().getId(), messageHeaders);
//        log.info("[onMessage][线程编号:{} map：{}]", Thread.currentThread().getId(), map);

    }
//    @DltHandler
//    public void processMessage(ConsumerRecord<String, String> record) {
//        log.info("进入死刑队列: {},[线程编号:{}]" ,record.value(),Thread.currentThread().getId());
//    }
}
@Data
@ToString
class Student {
    private String name;
    private int age;

    // 构造方法、getter、setter
}


