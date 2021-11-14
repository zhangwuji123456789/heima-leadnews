package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.model.article.dto.ApUserRealnameDto;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.itheima.api.article.AuthorControllerApi;
import com.itheima.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 15:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController implements AuthorControllerApi {
    @Autowired
    private AuthorService authorService;
    /**
     * 根据ID查询作者信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findByUserId/{id}")
    @Override
    public ApAuthor findByUserId(@PathVariable("id") Integer id) {
        List<ApAuthor> list = authorService.list(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getUserId,id));
        if (list != null &&!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存作者信息
     *
     * @param apAuthor
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody ApAuthor apAuthor) {

        apAuthor.setCreatedTime(new Date());
        authorService.save(apAuthor);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
