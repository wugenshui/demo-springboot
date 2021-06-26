package com.github.wugenshui.quick.start.entity;

import com.github.wugenshui.quick.start.enums.HttpResultEnum;

/**
 * Ajax请求响应类
 *
 * @author : chenbo
 * @date : 2019-12-07
 */
public class AjaxResult<T> {
    /**
     * 返回状态:参照 HttpResultEnum 枚举
     */
    private Integer state;

    /**
     * 返回的消息提示
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public AjaxResult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public AjaxResult(Integer state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功 默认提示
     *
     * @return 返回结果
     */
    public static AjaxResult success() {
        return new AjaxResult(HttpResultEnum.SUCCESS.getState(), HttpResultEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 返回成功 默认提示
     *
     * @param data 响应数据
     * @return 响应
     */
    public static <T> AjaxResult success(T data) {
        return new AjaxResult(HttpResultEnum.SUCCESS.getState(), HttpResultEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 返回成功 自定义提示
     *
     * @param message 响应消息
     * @param data    响应数据
     * @return 响应
     */
    public static <T> AjaxResult success(String message, T data) {
        return new AjaxResult(HttpResultEnum.SUCCESS.getState(), message, data);
    }

    /**
     * 返回系统异常错误
     *
     * @return 响应
     */
    public static AjaxResult error() {
        return new AjaxResult(HttpResultEnum.ERROR.getState(), HttpResultEnum.ERROR.getMsg());
    }

    /**
     * 返回失败 自定义错误
     *
     * @param message 响应消息
     * @return 响应
     */
    public static AjaxResult error(String message) {
        return new AjaxResult(HttpResultEnum.ERROR.getState(), message);
    }

    public int getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 当前请求是否执行成功
     *
     * @return 执行是否成功
     */
    public Boolean isSuccess() {
        return HttpResultEnum.SUCCESS.getState().equals(this.getState());
    }

    /**
     * 当前请求是否执行失败
     *
     * @return 执行是否失败
     */
    public Boolean isFail() {
        return !HttpResultEnum.SUCCESS.getState().equals(this.getState());
    }

}
