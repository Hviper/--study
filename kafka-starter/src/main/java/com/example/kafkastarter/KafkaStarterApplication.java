package com.example.kafkastarter;

import com.example.kafkastarter.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaStarterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KafkaStarterApplication.class, args);
        AppProperties bean = run.getBean(AppProperties.class);
        System.out.println(bean);
    }

}
