package com.github.wugenshui.knife4j.config;

import com.github.wugenshui.knife4j.entity.AjaxResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 参数校验异常
     *
     * @param request   请求
     * @param exception 异常
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult<String> MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception {
        FieldError error = exception.getBindingResult().getFieldError();
        return AjaxResult.error("参数校验失败:" + error.getDefaultMessage());
    }

    /**
     * 拦截所有未处理异常
     *
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult<String> exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        return AjaxResult.error(e.getMessage());
    }
}