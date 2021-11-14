package com.itheima.admin.controller;

import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import com.itheima.api.admin.AdUserControllerApi;
import com.itheima.service.AdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/31 17:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/login")
public class AdUserController implements AdUserControllerApi {
    @Autowired
    private AdUserService adUserService;
    /**
     * 用户登陆
     *
     * @param adUserDto
     * @return
     */

    @Override
    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto adUserDto) {
        ResponseResult result = adUserService.login(adUserDto);
        return result;
    }
}
