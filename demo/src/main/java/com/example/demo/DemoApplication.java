package com.example.demo;

import com.example.demo.bean.ClassMy;
import com.example.demo.bean.HHH;
import com.example.mybatis.bean.MyBaitsTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
@MapperScan
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        System.out.println("----------------------------------------");
        ClassMy bean = run.getBean(ClassMy.class);
        MyBaitsTest myBaitsTest = run.getBean(MyBaitsTest.class);
        HHH hhh = run.getBean(HHH.class);
        System.out.println(bean);
        System.out.println(myBaitsTest);
        System.out.println(hhh);
        System.out.println("----------------------------------------");
    }
}
