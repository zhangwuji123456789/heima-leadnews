package com.heima.model.admin.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 21:02
 * @Version 1.0
 */
@Data
public class AdSensitiveDto extends PageRequestDto {
    /**
     * 敏感词名称
     */
    private String name;
}
