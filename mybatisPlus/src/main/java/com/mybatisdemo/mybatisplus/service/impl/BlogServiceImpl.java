package com.mybatisdemo.mybatisplus.service.impl;

import com.mybatisdemo.mybatisplus.bean.Blog;
import com.mybatisdemo.mybatisplus.mapper.BlogMapper;
import com.mybatisdemo.mybatisplus.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 爱迪生
 * @since 2024-06-22
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
