package com.mybatisdemo.mybatisplus.service;


import com.mybatisdemo.mybatisplus.bean.Student;
import com.mybatisdemo.mybatisplus.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class TransactionalService {
    @Autowired
    StudentMapper studentMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insertUser(Student student) throws Exception
    {

        // 新增用户信息
        int rows = studentMapper.insert(student);
        // 新增用户岗位关联
        // 新增用户与角色管理
        // 模拟抛出SQLException异常
        boolean flag = true;
        if (flag)
        {
            throw new SQLException("发生异常了..");
        }
        return rows;
    }
}
