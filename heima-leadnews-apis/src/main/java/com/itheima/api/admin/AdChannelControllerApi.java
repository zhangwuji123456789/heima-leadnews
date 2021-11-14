package com.itheima.api.admin;

import com.heima.model.admin.dtos.AdChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/24 16:13
 * @Version 1.0
 */
@Api(value = "频道管理", tags = "channel", description = "频道管理API")
public interface AdChannelControllerApi {
    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    @ApiOperation("频道分页列表查询")
    ResponseResult findByNameAndPage(AdChannelDto dto);

    /**
     * 新增频道列表
     * @param adChannel
     * @return
     */
    @ApiOperation("新增频道列表")
    ResponseResult insert(AdChannel adChannel);

    /**
     * 根据名称分页查询频道列表
     * @param adChannel
     * @return
     */
    @ApiOperation("频道列表更新")
    ResponseResult update(Integer id, AdChannel adChannel);

    /**
     * 根据名称分页查询频道列表
     * @param id
     * @return
     */
    @ApiOperation("删除频道")
    ResponseResult delete(Integer id);

}
