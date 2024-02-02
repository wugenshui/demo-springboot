package com.github.wugenshui.prometheus.demo.controller;

import com.github.wugenshui.prometheus.demo.config.MetricsMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : chenbo
 * @date : 2024-02-02
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Resource
    private MetricsMonitor monitor;

    @GetMapping
    public Object get() throws InterruptedException {
        long startTimeMillis = System.currentTimeMillis();

        Double typeRandom = Math.random();
        String aiType = "算法" + ((Double) (typeRandom * 10)).intValue();
        Double stateRandom = Math.random();
        Boolean state = stateRandom > 0.8;

        String success = "1";
        if (state) {
            // 模拟接口请求失败
            success = "0";
            Thread.sleep(((Double) (typeRandom * 500)).longValue());
        } else {
            Thread.sleep(((Double) (typeRandom * 1000)).longValue());
        }
        long costTimeMillis = System.currentTimeMillis() - startTimeMillis;
        // 记录指标
        monitor.setAiCount(aiType, success);
        monitor.setAiAmountSum(aiType, success, 1);
        monitor.setAiTime(aiType, success, costTimeMillis);
        return typeRandom;
    }
}
