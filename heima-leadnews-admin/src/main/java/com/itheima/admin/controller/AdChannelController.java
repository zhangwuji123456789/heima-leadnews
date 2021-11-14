package com.itheima.admin.controller;

import com.heima.model.admin.dtos.AdChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.itheima.api.admin.AdChannelControllerApi;
import com.itheima.service.AdChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/24 17:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {
    @Autowired
    private AdChannelService adChannelService;
    /**
     * 根据名称分页查询频道列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult findByNameAndPage(AdChannelDto dto) {
        ResponseResult result = adChannelService.findByNameAndPage(dto);
        return result;

    }

    /**
     * 新增频道列表
     *
     * @param adChannel
     * @return
     */
    @PostMapping
    @Override
    public ResponseResult insert(@RequestBody AdChannel adChannel) {
        ResponseResult result = adChannelService.insert(adChannel);
        return result;
    }

    /**
     * 根据名称分页查询频道列表
     *
     * @param id
     * @param adChannel
     * @return
     */
    @PutMapping("/{id}")
    @Override
    public ResponseResult update(@PathVariable(value = "id") Integer id, @RequestBody AdChannel adChannel) {
        ResponseResult result = adChannelService.update(id, adChannel);
        return result;
    }

    /**
     * 根据名称分页查询频道列表
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Override
    public ResponseResult delete(@PathVariable(value = "id")Integer id) {
        ResponseResult result = adChannelService.delete(id);
        return result;
    }


}
