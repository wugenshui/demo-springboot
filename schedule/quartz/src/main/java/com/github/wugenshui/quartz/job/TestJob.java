package com.github.wugenshui.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义任务
 *
 * @author : chenbo
 * @date : 2023-08-22
 */
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("开启数据备份，当前时间：" + data);
    }
}