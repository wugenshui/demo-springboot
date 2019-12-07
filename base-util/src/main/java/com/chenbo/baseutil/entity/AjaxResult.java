package com.chenbo.baseutil.entity;

import com.chenbo.baseutil.enums.HttpResultEnum;
import com.chenbo.baseutil.util.MapUtil;

import java.util.Map;

/**
 * Ajax请求响应类
 *
 * @author : chenbo
 * @date : 2019-12-07
 */
public class AjaxResult<T> {
    /**
     * 返回状态:参照HttpResultEnum枚举
     */
    private int state;
    /**
     * 返回的消息提示
     */
    private String message;
    /**
     * 返回的数据
     */
    private T data;

    public AjaxResult(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public AjaxResult(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    /**
     * 转换成map
     *
     * @return
     */
    public Map<String, Object> covMap() {
        return MapUtil.build().put("state", this.state).put("message", this.message).put("data", this.data).over();
    }

    /**
     * 返回成功 默认提示
     *
     * @return
     */
    public static AjaxResult success() {
        HttpResultEnum model = HttpResultEnum.SUCCESS;
        return new AjaxResult(model.getStatus(), model.getMessage());
    }

    /**
     * 返回成功 默认提示
     *
     * @param data
     * @return
     */
    public static <T> AjaxResult success(T data) {
        HttpResultEnum model = HttpResultEnum.SUCCESS;
        return new AjaxResult(model.getStatus(), model.getMessage(), data);
    }

    /**
     * 返回成功 自定义提示
     *
     * @param message
     * @param data
     * @return
     */
    public static <T> AjaxResult success(String message, T data) {
        return new AjaxResult(HttpResultEnum.SUCCESS.getStatus(), message, data);
    }

    /**
     * 返回系统异常错误
     *
     * @return
     */
    public static AjaxResult error() {
        HttpResultEnum model = HttpResultEnum.ERROR;
        return new AjaxResult(model.getStatus(), model.getMessage());
    }

    /**
     * 返回失败 自定义错误
     *
     * @param message
     * @return
     */
    public static AjaxResult error(String message) {
        return new AjaxResult(HttpResultEnum.ERROR.getStatus(), message);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
