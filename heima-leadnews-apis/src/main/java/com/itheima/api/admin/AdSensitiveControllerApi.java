package com.itheima.api.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdSensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 21:05
 * @Version 1.0
 */
@Api(value = "频道管理", tags = "sensitive", description = "敏感词管理API")
public interface AdSensitiveControllerApi {

    /**
     * 根据名称分页查询敏感词列表
     * @param adSensitiveDto
     * @return
     */
    @ApiOperation("频道分页列表查询")
    ResponseResult finByNameAndPage(AdSensitiveDto adSensitiveDto);

    /**
     * 根据ID更新敏感词列表
     * @param adSensitive
     * @return
     */
    @ApiOperation("更新敏感词")
    ResponseResult update(Integer id,AdSensitive adSensitive);

    /**
     * 根据名称新增敏感词列表
     * @param adSensitive
     * @return
     */
    @ApiOperation("新增敏感词")
    ResponseResult insert(AdSensitive adSensitive);

    /**
     * 根据名称删除敏感词列表
     * @param id
     * @return
     */
    @ApiOperation("删除敏感词")
    ResponseResult delete(Integer id);
}
