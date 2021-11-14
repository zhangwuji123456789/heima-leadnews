package com.heima.model.common.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageRequestDto {

    // 每页显示的条数
    @ApiModelProperty(value="每页显示条数",required = true)
    protected Integer size;

    // 当前页码
    @ApiModelProperty(value="当前页",required = true)
    protected Integer page;

    // 初始化分页参数
    public void checkParam() {
        if (this.page == null || this.page < 0) {
            setPage(1);
        }
        if (this.size == null || this.size < 0 || this.size > 100) {
            setSize(10);
        }
    }
}
