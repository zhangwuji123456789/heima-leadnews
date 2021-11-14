package com.itheima.api.admin;

import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/31 17:03
 * @Version 1.0
 */
@Api(value = "用户登陆", tags = "user", description = "敏感词管理API")
public interface AdUserControllerApi {
    /**
     * 用户登陆
     * @param adUserDto
     * @return
     */
    @ApiOperation("用户登陆")
    ResponseResult login(AdUserDto adUserDto);
}
