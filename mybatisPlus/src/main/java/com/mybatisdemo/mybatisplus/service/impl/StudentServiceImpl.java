package com.mybatisdemo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisdemo.mybatisplus.bean.Student;
import com.mybatisdemo.mybatisplus.mapper.StudentMapper;
import com.mybatisdemo.mybatisplus.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {
    public List<Student> getStudentById(Integer id,String name,Integer age) {
        return lambdaQuery().eq(id==null,Student::getId, id)
                .ge(name==null,Student::getUsername,name).list();
    }

    public Page<Student> pages(Page<Student> objectPage, LambdaQueryWrapper<Student> studentQueryWrapper) {
        return baseMapper.pages(objectPage,studentQueryWrapper);
    }
}
