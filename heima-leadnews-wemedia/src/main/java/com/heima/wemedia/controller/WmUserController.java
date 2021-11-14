package com.heima.wemedia.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heima.model.Wemedia.pojos.WmUser;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.wemedia.service.WmUserService;
import com.itheima.api.wemedia.WmUserControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/14 16:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/wmuser")
public class WmUserController implements WmUserControllerApi {

    @Autowired
    private WmUserService wmUserService;
    /**
     * 保存自媒体用户
     *
     * @param wmUser
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult insert(@RequestBody WmUser wmUser) {
        wmUser.setCreatedTime(new Date());
        wmUserService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }

    /**
     * 根据名称查询自媒体用户
     *
     * @param name
     * @return
     */
    @GetMapping("/findByName/{name}")
    @Override
    public WmUser findName(@PathVariable(value = "name")String name) {
        LambdaQueryWrapper<WmUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(WmUser::getName,name);
        List<WmUser> list = wmUserService.list(queryWrapper);
        if (list != null && list.size() > 0 ){
            return list.get(0);
        }
        return null;
    }
}
