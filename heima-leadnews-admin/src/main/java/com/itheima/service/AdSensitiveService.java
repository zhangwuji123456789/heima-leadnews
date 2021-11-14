package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.AdSensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 21:08
 * @Version 1.0
 */
public interface AdSensitiveService extends IService<AdSensitive> {
    /**
     * 根据名称分页查询敏感词列表
     * @param adSensitiveDto
     * @return
     */
    ResponseResult finByNameAndPage(AdSensitiveDto adSensitiveDto);

    /**
     * 根据ID更新敏感词列表
     * @param adSensitive
     * @return
     */
    ResponseResult update(Integer id, AdSensitive adSensitive);

    /**
     * 根据名称新增敏感词列表
     * @param adSensitive
     * @return
     */
    ResponseResult insert(AdSensitive adSensitive);

    /**
     * 根据名称删除敏感词列表
     * @param id
     * @return
     */
    ResponseResult delete(Integer id);
}
