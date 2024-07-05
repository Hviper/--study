package com.mybatisdemo.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisdemo.mybatisplus.bean.Student;
import com.mybatisdemo.mybatisplus.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    StudentServiceImpl service;
    @Test
    void contextLoads() throws Exception {
        Page<Student> page = service.page(new Page<>(1, 10));
        page.getRecords().forEach(System.out::println);
    }
    @Test
    public void test01(){
        Page<Student> objectPage = new Page<>(1, 10);
        LambdaQueryWrapper<Student> studentQueryWrapper = new LambdaQueryWrapper<>();
        Page<Student> pages = service.pages(objectPage, studentQueryWrapper);
        System.out.println(pages == objectPage);
        for (Student record : objectPage.getRecords()) {
            System.out.println(record);
        }
    }
}
