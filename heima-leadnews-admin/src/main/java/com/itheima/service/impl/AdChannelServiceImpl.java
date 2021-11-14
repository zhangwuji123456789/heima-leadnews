package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.AdChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.service.AdChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.FaultAction;
import java.util.Date;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/24 16:31
 * @Version 1.0
 */
@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {

    @Autowired(required = false)
    private AdChannelMapper adChannelMapper;

    /**
     * 根据名称分页查询频道列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findByNameAndPage(AdChannelDto dto) {
        //1.设置参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //参数初始化
        dto.checkParam();
        //设置分页条件
        Page<AdChannel> page = new Page<>(dto.getPage(), dto.getSize());
        //查询分页参数
        LambdaQueryWrapper<AdChannel> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(dto.getName())) {
            queryWrapper.like(AdChannel::getName, dto.getName());
        }
        IPage<AdChannel> iPage = adChannelMapper.selectPage(page, queryWrapper);
        //结果封装
        PageResponseResult result = new PageResponseResult((int) iPage.getCurrent(), (int) iPage.getSize(), (int) iPage.getTotal());
        result.setData(iPage.getRecords());
        return result;
    }

    /**
     * 根据ID更新频道列表
     *
     * @param id
     * @param adChannel
     * @return
     */
    @Override
    public ResponseResult update(Integer id, AdChannel adChannel) {
        if (adChannel == null || id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        adChannel.setId(id);
        boolean bool = updateById(adChannel);
        if (bool) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 新增频道列表
     *
     * @param adChannel
     * @return
     */
    @Override
    public ResponseResult insert(AdChannel adChannel) {
        if (adChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        adChannel.setCreatedTime(new Date());
        Boolean bool = save(adChannel);
        if (bool) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 删除频道列表
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult delete(Integer id) {
        if (id == null){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询是否有这条数据
        AdChannel adChannel = getById(id);
        if (adChannel == null){
            ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //只能删除无效状态的数据
        if (adChannel.getStatus()){
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"不能删除有效的数据");
        }
        boolean bool = removeById(id);
        if (bool){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }

        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}
