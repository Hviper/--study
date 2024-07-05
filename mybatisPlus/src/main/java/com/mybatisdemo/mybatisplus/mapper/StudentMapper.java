package com.mybatisdemo.mybatisplus.mapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisdemo.mybatisplus.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper extends BaseMapper<Student> {
    @Select("select username from  student  ${ew.customSqlSegment} ")
    public Page<Student> pages(Page<Student> page, @Param(Constants.WRAPPER)Wrapper<Student> queryWrapper);
}
