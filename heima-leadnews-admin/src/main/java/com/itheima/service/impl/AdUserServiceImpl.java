package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import com.itheima.admin.mapper.AdUserMapper;
import com.itheima.service.AdUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/31 17:08
 * @Version 1.0
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {
    @Override
    public ResponseResult login(AdUserDto adUserDto) {
        //参数检查
        if (adUserDto == null){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //判断账号密码是否为空
        if (StringUtils.isEmpty(adUserDto.getName()) || StringUtils.isEmpty(adUserDto.getPassword())){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"账号或密码错误");
        }
        //根据用户名查询
        LambdaQueryWrapper<AdUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdUser::getName,adUserDto.getName());
        AdUser adUser = getOne(queryWrapper);
        //判断用户是存在
        if (adUser == null){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"您的用户不存在");
        }
        //判断密码是否正确，首先获得盐值，然后密码加盐
        String salt = adUser.getSalt();
        String asHex = DigestUtils.md5DigestAsHex((adUser.getPassword() + salt).getBytes());
        if (!asHex.equals(adUser.getPassword())){
            ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        String token = AppJwtUtil.getToken(adUser.getId().longValue());
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("adUser",adUser);
        return ResponseResult.okResult(map);


    }
}
