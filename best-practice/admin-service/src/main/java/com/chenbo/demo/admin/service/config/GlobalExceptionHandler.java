package com.chenbo.demo.admin.service.config;

import com.chenbo.demo.admin.service.dto.AjaxResult;
import com.chenbo.demo.admin.service.enums.AjaxResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        assert error != null;
        return AjaxResult.error(StringUtils.defaultIfBlank("参数校验异常:" + error.getDefaultMessage() + "!", "参数校验异常！"));
    }

    /**
     * 处理其它未处理异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult handleOtherException(Exception ex) {
        log.error("系统全局异常", ex);
        if (log.isInfoEnabled()) {
            return AjaxResult.error(ex.getMessage());
        }
        return AjaxResult.error(AjaxResultEnum.ERROR.getMsg());
    }
}