package com.github.wugenshui.prometheus.demo.config;

import io.micrometer.core.instrument.Metrics;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 指标监控组件
 *
 * @author : chenbo
 * @date : 2024-01-26
 */
@Component
public class MetricsMonitor {
    @Autowired
    private CollectorRegistry registry;

    /**
     * label: 接口请求算法分类
     */
    public static final String LABEL_AI_TYPE = "aiType";

    /**
     * label: 接口请求是否成功，成功为1，失败为0
     */
    public static final String LABEL_AI_SUCCESS = "aiSuccess";

    /**
     * 接口请求数
     */
    private Counter aiCount;

    /**
     * 接口统计
     */
    private Summary aiAmountSum;

    @PostConstruct
    private void init() {
        // 暴露指标：xxx_total xxx_created
        aiCount = Counter.build().name("ai_request_count")
                .help("ai request count")
                .labelNames(LABEL_AI_TYPE, LABEL_AI_SUCCESS)
                .register();
        // 暴露指标：xxx_count xxx_sum xxx_created
        aiAmountSum = Summary.build().name("ai_request_amount_sum")
                .help("ai request amount sum")
                .labelNames(LABEL_AI_TYPE, LABEL_AI_SUCCESS)
                .register();
        registry.register(aiCount);
        registry.register(aiAmountSum);
    }

    /**
     * 设置接口请求次数
     *
     * @param aiType  算法类型
     * @param success 是否成功
     */
    public void setAiCount(String aiType, String success) {
        aiCount.labels(aiType, success).inc();
    }

    /**
     * 设置请求统计
     *
     * @param aiType  算法类型
     * @param success 是否成功
     * @param amount  数量
     */
    public void setAiAmountSum(String aiType, String success, double amount) {
        aiAmountSum.labels(aiType, success).observe(amount);
    }

    /**
     * 设置请求时间
     *
     * @param aiType         算法类型
     * @param success        是否成功
     * @param costTimeMillis 耗时
     */
    public void setAiTime(String aiType, String success, long costTimeMillis) {
        // 暴露指标：xxx_seconds_count xxx_seconds_sum xxx_seconds_max
        Metrics.timer("ai_request_time", LABEL_AI_TYPE, aiType, LABEL_AI_SUCCESS, success).record(costTimeMillis, TimeUnit.MILLISECONDS);
    }

}
