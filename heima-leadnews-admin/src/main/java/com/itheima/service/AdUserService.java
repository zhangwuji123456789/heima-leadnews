package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/31 17:07
 * @Version 1.0
 */
public interface AdUserService extends IService<AdUser> {
    ResponseResult login(AdUserDto adUserDto);
}
