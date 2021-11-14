package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/24 16:29
 * @Version 1.0
 */
public interface AdChannelService extends IService<AdChannel> {

    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    ResponseResult findByNameAndPage(AdChannelDto dto);


    /**
     * 根据ID更新频道列表
     * @param adChannel
     * @return
     */
    ResponseResult update(Integer id,AdChannel adChannel);

    /**
     * 新增频道列表
     * @param adChannel
     * @return
     */
    ResponseResult insert(AdChannel adChannel);


    /**
     * 删除频道列表
     * @param id
     * @return
     */
    ResponseResult delete(Integer id);
}
