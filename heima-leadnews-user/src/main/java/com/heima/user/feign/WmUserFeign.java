package com.heima.user.feign;

import com.heima.model.Wemedia.pojos.WmUser;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 16:48
 * @Version 1.0
 */
@FeignClient(name = "leadnews-wemedia")
public interface WmUserFeign {
    /**
     * 保存用户
     * @param wmUser
     * @return
     */
    @PostMapping("/api/v1/wmuser/save")
    ResponseResult insert(@RequestBody WmUser wmUser);

    /**
     * 根据用户名查询自媒体用户
     * @param name
     * @return
     */
    @GetMapping("/api/v1/wmuser/findByName/{name}")
    WmUser findByName(@PathVariable(value = "name") String name);
}
