package com.itheima.api.admin;

import com.heima.model.article.dto.ApUserRealnameDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/8 17:34
 * @Version 1.0
 */
@Api(value = "App", tags = "ApUserRealname", description = "用戶列表查詢")
public interface ApUserRealnameControllerApi {
    /**
     * 根据条件查询app端用户列表
     * @param apUserRealnameDto
     * @return
     */
    ResponseResult loadListByStatus(ApUserRealnameDto apUserRealnameDto);

    /**
     * 用户审核通过
     * @param dto
     * @return
     */
    ResponseResult auditPass(ApUserRealnameDto dto);
    /**
     * 用户审核不通过
     * @param dto
     * @return
     */
    ResponseResult auditFail(ApUserRealnameDto dto);
}
