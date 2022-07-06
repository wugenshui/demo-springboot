package com.github.wugenshui.best.admin.service.config;

import com.github.wugenshui.best.admin.service.dto.AjaxResult;
import com.github.wugenshui.best.admin.service.enums.AjaxResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
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
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        assert error != null;
        return AjaxResult.error(StringUtils.defaultIfBlank(error.getDefaultMessage(), "参数校验异常！"));
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        assert error != null;
        return AjaxResult.error(StringUtils.defaultIfBlank(error.getDefaultMessage(), "参数校验异常！"));
    }

    /**
     * 处理未授权接口访问
     */
    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAccessDeniedException(AccessDeniedException ex) {
        return new AjaxResult(AjaxResultEnum.UNAUTH.getState(), "未授权的接口");
    }

    /**
     * 处理其它未处理异常
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult handleOtherException(Exception ex) {
        log.error("系统全局异常", ex);
        if (log.isInfoEnabled()) {
            return AjaxResult.error(StringUtils.defaultIfBlank(ex.getMessage(), AjaxResultEnum.ERROR.getMsg()));
        }
        return AjaxResult.error(AjaxResultEnum.ERROR.getMsg());
    }
}