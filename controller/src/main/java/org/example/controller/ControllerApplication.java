package org.example.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy

public class ControllerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ControllerApplication.class, args);

    }

}
