package com.github.wugenshui.spring.cloud.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-01-14
 */
@RestController
@RequestMapping
public class TestController {

    @SentinelResource(value = "test", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @GetMapping
    public String test() {
        return "test";
    }

    /**
     * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     */
    public String exceptionHandler(BlockException ex) {
        ex.printStackTrace();
        return "异常发生，请求阻断了！";
    }

    /**
     * fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     */
    public String helloFallback() {
        return String.format("主动降级！！");
    }
}
