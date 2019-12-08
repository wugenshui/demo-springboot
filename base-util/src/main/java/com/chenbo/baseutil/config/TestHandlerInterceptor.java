package com.chenbo.baseutil.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Slf4j
public class TestHandlerInterceptor implements HandlerInterceptor {
    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // log.info("LogHandlerInterceptor.preHandle"); 编码、安全控制、权限校验
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染）
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // log.info("LogHandlerInterceptor.postHandle"); 后处理
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // log.info("LogHandlerInterceptor.afterCompletion"); 清理资源
    }
}
