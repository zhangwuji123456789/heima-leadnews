package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.pojos.ApAuthor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 15:58
 * @Version 1.0
 */
@Mapper
public interface AuthorMapper extends BaseMapper<ApAuthor> {
}
