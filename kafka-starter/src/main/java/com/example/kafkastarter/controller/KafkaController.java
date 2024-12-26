package com.example.kafkastarter.controller;

import com.example.kafkastarter.produce.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/send")
    public String sendMessage() {
        kafkaProducer.sendMessage("Hello from Spring Boot and Kafka!");
        return "Message sent!";
    }
}

