package com.heima.common.exception;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Ma zhi lin
 * @Date 2021/7/26 20:55
 * @Version 1.0
 * 统一异常处理器
 */
@ControllerAdvice
@Log4j2
public class ExceptionCatch {
    // 捕获Exception此类异常
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseResult exception(Exception exception) {
        exception.printStackTrace();
        // 记录日志
        log.error("catch exception:{}", exception.getMessage());
        // 返回通用异常
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}
