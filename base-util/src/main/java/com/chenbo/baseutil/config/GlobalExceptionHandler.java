package com.chenbo.baseutil.config;

import com.chenbo.baseutil.entity.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult handleException(HttpServletRequest request, Exception ex) {
        log.error("全局异常:{}", ex);
        return AjaxResult.error("全局异常：" + ex);
    }
}