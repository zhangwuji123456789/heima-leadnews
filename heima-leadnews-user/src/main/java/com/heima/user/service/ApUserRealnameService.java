package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dto.ApUserRealnameDto;
import com.heima.model.admin.pojos.ApUserRealname;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/8 17:53
 * @Version 1.0
 */
public interface ApUserRealnameService extends IService<ApUserRealname> {
    /**
     * APP端查詢用戶列表
     * @param apUserRealnameDto
     * @return
     */
    ResponseResult loadListByStatus(ApUserRealnameDto apUserRealnameDto);

    /**
     * 审核用户
     * @param dto
     * @param status
     * @return
     */
    ResponseResult updateStatusById(ApUserRealnameDto dto, Short status);
}
