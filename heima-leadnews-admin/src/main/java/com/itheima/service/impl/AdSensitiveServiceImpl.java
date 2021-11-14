package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.AdSensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.admin.mapper.AdSensitiveMapper;
import com.itheima.service.AdSensitiveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 21:08
 * @Version 1.0
 */
@Service
public class AdSensitiveServiceImpl extends ServiceImpl<AdSensitiveMapper,AdSensitive> implements AdSensitiveService {

    @Autowired(required = false)
    private AdSensitiveMapper adSensitiveMapper;
    /**
     * 根据名称分页查询敏感词列表
     *
     * @param adSensitiveDto
     * @return
     */
    @Override
    public ResponseResult finByNameAndPage(AdSensitiveDto adSensitiveDto) {
        if (adSensitiveDto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //初始化
        adSensitiveDto.checkParam();
        // 3、查询
        // 3-1、封装分页条件
        Page<AdSensitive> page = new Page<>(adSensitiveDto.getPage(),adSensitiveDto.getSize());
        // 3-2、封装查询条件
        LambdaQueryWrapper<AdSensitive> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNoneEmpty(adSensitiveDto.getName())){
            queryWrapper.like(AdSensitive::getSensitives,adSensitiveDto.getName());
        }
        // 4、结果集封装
        IPage<AdSensitive> iPage = adSensitiveMapper.selectPage(page,queryWrapper);
        PageResponseResult pageResponseResult = new PageResponseResult((int)iPage.getCurrent(),(int)iPage.getSize(),(int)iPage.getTotal());
        pageResponseResult.setData(iPage.getRecords());
        return pageResponseResult;
    }

    /**
     * 根据ID更新敏感词列表
     *
     * @param id
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult update(Integer id, AdSensitive adSensitive) {
        if (id == null || adSensitive == null){
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        adSensitive.setId(id);
        boolean bool = updateById(adSensitive);
        if (bool){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }

        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 根据名称新增敏感词列表
     *
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult insert(AdSensitive adSensitive) {
        if (adSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        adSensitive.setCreatedTime(new Date());
        boolean bool = save(adSensitive);
        if (bool){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 根据名称删除敏感词列表
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(Integer id) {
        if (id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //判断是否有这条数据
        AdSensitive adSensitive = getById(id);
        if (adSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        boolean bool = removeById(id);
        if (bool){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}
