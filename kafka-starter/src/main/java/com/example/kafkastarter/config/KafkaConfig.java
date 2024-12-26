package com.example.kafkastarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(1); // Set retry attempts to 1
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;
    }

//
//
//    @Bean
//    public RetryTopicConfiguration myRetryableTopic(KafkaTemplate<String, Object> template) {
//        List<String> list = Arrays.asList("test-topic", "my-other-topic");
//        return RetryTopicConfigurationBuilder
//                .newInstance()
//
//                .maxAttempts(1)
//                .includeTopics(list)
//                .create(template);
//    }



}

