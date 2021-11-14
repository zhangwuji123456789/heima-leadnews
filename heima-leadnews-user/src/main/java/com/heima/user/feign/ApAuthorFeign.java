package com.heima.user.feign;

import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 16:48
 * @Version 1.0
 */
@FeignClient(name = "leadnews-article")
public interface ApAuthorFeign {
    /**
     * 根据ID查询作者
     * @param id
     * @return
     */
    @GetMapping("/api/v1/author/findByUserId/{id}")
    ApAuthor findById(@PathVariable(value = "id") Integer id);

    /**
     * 保存作者
     * @param apAuthor
     * @return
     */
    @PostMapping("/api/v1/apauthor/save")
    ResponseResult insert(@RequestBody ApAuthor apAuthor);

}
