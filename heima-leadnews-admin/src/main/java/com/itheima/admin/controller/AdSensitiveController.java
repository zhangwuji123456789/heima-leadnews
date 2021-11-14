package com.itheima.admin.controller;

import com.heima.model.admin.dtos.AdSensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import com.itheima.api.admin.AdSensitiveControllerApi;
import com.itheima.service.AdSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 21:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class AdSensitiveController implements AdSensitiveControllerApi {
    @Autowired
    private AdSensitiveService adSensitiveService;
    /**
     * 根据名称分页查询敏感词列表
     *
     * @param adSensitiveDto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult finByNameAndPage(@RequestBody AdSensitiveDto adSensitiveDto) {
        ResponseResult responseResult = adSensitiveService.finByNameAndPage(adSensitiveDto);
        return responseResult;
    }

    /**
     * 根据ID更新敏感词列表
     *
     * @param id
     * @param adSensitive
     * @return
     */
    @PutMapping("/{id}")
    @Override
    public ResponseResult update(@PathVariable(value = "id") Integer id, @RequestBody AdSensitive adSensitive) {
        ResponseResult responseResult = adSensitiveService.update(id,adSensitive);
        return responseResult;
    }

    /**
     * 根据名称新增敏感词列表
     *
     * @param adSensitive
     * @return
     */
    @PostMapping
    @Override
    public ResponseResult insert(@RequestBody AdSensitive adSensitive) {
        ResponseResult responseResult = adSensitiveService.insert(adSensitive);
        return responseResult;
    }

    /**
     * 根据名称删除敏感词列表
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseResult delete(@PathVariable(value = "id")Integer id) {
        ResponseResult responseResult = adSensitiveService.delete(id);
        return responseResult;
    }
}
