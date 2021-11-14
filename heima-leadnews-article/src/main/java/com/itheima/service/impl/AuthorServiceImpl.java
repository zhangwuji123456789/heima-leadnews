package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.article.pojos.ApAuthor;
import com.itheima.mapper.AuthorMapper;
import com.itheima.service.AuthorService;
import org.springframework.stereotype.Service;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 16:00
 * @Version 1.0
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, ApAuthor> implements AuthorService {
}
