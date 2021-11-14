package com.heima.model.article.dto;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/8 17:31
 * @Version 1.0
 */
@Data
public class ApUserRealnameDto extends PageRequestDto {

    private Integer id;
    // 驳回的信息
    private String msg;
    // 状态
    private Short status;
}
