package com.github.wugenshui.aop.test.aop0;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义方法拦截器
 *
 * @author : chenbo
 * @date : 2023-11-13
 */
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("MyMethodInterceptor：请求方法={}, 请求参数={}", invocation.getStaticPart(), invocation.getArguments());
        Object proceed = invocation.proceed();
        if (proceed instanceof String) {
            proceed = "MyMethodInterceptor：" + proceed;
        }
        log.info("MyMethodInterceptor：响应结果={}", proceed);
        return proceed;
    }
}
