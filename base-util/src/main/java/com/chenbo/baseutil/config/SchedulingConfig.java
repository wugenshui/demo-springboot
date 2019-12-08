package com.chenbo.baseutil.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {

    /**
     * Corn表达式 每2h执行一次
     */
    @Scheduled(cron = "0 0 0-2 * * ? ")
    public void scheduler() {
        log.info("每2h执行一次！！！");
    }
}
