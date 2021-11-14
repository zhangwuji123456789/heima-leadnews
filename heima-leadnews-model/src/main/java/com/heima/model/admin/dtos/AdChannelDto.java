package com.heima.model.admin.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.heima.model.common.dtos.PageResponseResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/24 15:53
 * @Version 1.0
 */
@Data
public class AdChannelDto extends PageRequestDto {
    /**
     * 频道名称
     */
    @ApiModelProperty("频道名称")
    private String name;
}
