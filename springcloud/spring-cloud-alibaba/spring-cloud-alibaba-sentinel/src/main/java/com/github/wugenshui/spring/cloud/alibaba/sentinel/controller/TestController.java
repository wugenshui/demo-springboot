package com.github.wugenshui.spring.cloud.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 服务限流示例
 * 限流：服务正常，限制过高频率的访问
 * 新增流控规则，配置每秒查询率QPS=1，限制每秒仅能请求1次，如果超出会跳转回该函数
 *
 * @author : chenbo
 * @SentinelResource 标识为一个可以控制的资源，blockHandler通过本类中的函数进行响应，也可以通过类
 * @date : 2021-01-14
 */
@RestController
@RequestMapping
public class TestController {

    private AtomicLong atomicLong = new AtomicLong(1);

    @SentinelResource(value = "test", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @GetMapping
    public long test() throws InterruptedException {
        // 获取自增对象的值,然后再加1
        long num = atomicLong.getAndIncrement();
        // 模拟50%的慢调用比例
        if (num % 2 == 0) {
            Thread.sleep(200);
        }
        System.out.println("发生异常前");
        return num;
    }

    /**
     * block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     */
    public String exceptionHandler(BlockException ex) {
        System.out.println("ex = " + ex);
        ex.printStackTrace();
        return "异常发生，请求阻断了！";
    }

    /**
     * fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     */
    public String helloFallback() {
        System.out.println("主动降级");
        return String.format("主动降级！！");
    }
}
