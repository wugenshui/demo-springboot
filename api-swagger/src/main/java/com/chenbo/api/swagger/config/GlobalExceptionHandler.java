package com.chenbo.api.swagger.config;

import com.chenbo.api.swagger.entity.AjaxResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        String temp = "";
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return AjaxResult.error(msg);
    }

    /**
     * 拦截所有的异常
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