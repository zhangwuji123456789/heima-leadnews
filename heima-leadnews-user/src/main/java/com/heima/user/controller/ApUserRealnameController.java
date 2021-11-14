package com.heima.user.controller;

import com.heima.common.constants.user.UserConstants;
import com.heima.model.article.dto.ApUserRealnameDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.user.service.ApUserRealnameService;
import com.itheima.api.admin.ApUserRealnameControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/8 18:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/user")
public class ApUserRealnameController implements ApUserRealnameControllerApi {
    @Autowired
    private ApUserRealnameService apUserRealnameService;

    /**
     * 根据条件查询app端用户列表
     *
     * @param apUserRealnameDto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult loadListByStatus(@RequestBody ApUserRealnameDto apUserRealnameDto) {
        ResponseResult responseResult = apUserRealnameService.loadListByStatus(apUserRealnameDto);
        return responseResult;
    }

    /**
     * 用户审核通过
     *
     * @param dto
     * @return
     */
    @PostMapping("/auditPass")
    @Override
    public ResponseResult auditPass(@RequestBody ApUserRealnameDto dto) {
        ResponseResult responseResult = apUserRealnameService.updateStatusById(dto, UserConstants.PASS_AUTH);
        return responseResult;
    }

    /**
     * 用户审核不通过
     *
     * @param dto
     * @return
     */
    @PostMapping("/auditFail")
    @Override
    public ResponseResult auditFail(@RequestBody ApUserRealnameDto dto) {
        ResponseResult responseResult = apUserRealnameService.updateStatusById(dto, UserConstants.PASS_AUTH);
        return responseResult;
    }
}
