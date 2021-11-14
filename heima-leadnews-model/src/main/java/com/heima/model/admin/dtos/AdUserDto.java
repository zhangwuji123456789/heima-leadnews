package com.heima.model.admin.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Builder;
import lombok.Data;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/31 17:02
 * @Version 1.0
 */
@Data
public class AdUserDto extends PageRequestDto {
    // 用户名
    private String name;

    // 密码
    private String password;
}
