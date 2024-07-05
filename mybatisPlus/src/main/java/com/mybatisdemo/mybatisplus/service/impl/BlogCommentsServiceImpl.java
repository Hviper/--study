package com.mybatisdemo.mybatisplus.service.impl;

import com.mybatisdemo.mybatisplus.bean.BlogComments;
import com.mybatisdemo.mybatisplus.mapper.BlogCommentsMapper;
import com.mybatisdemo.mybatisplus.service.IBlogCommentsService;
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
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
