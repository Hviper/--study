package com.mybatisdemo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run =
                SpringApplication.run(MybatisPlusApplication.class, args);
        Thread.sleep(50000000);
    }

}
