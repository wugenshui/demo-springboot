package com.github.wugenshui.baseutil.baseutil.spring.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @author : chenbo
 * @date : 2020-03-06
 */
@SpringBootTest
public class BackOffTest {
    @Test
    public void fixedBackOffTest() {
        // 重试次数
        long interval = 1000;
        // 最大重试次数
        long maxAttempts = 5;
        BackOff backOff = new FixedBackOff(interval, maxAttempts);
        BackOffExecution execution = backOff.start();

        while (true) {
            long value = execution.nextBackOff();
            if (value == BackOffExecution.STOP) {
                System.out.println("FixedBackOff执行中止");
                break;
            } else {
                try {
                    Thread.sleep(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("FixedBackOff执行了一次:" + value);
            }
        }
    }

    @Test
    public void exponentialBackOffTest() {
        long initialInterval = 100;  //初始间隔
        double multiplier = 1.5; //递增倍数
        long maxInterval = 2 * 1000L;  //最大间隔
        long maxElapsedTime = 10 * 1000L;  //累计最大的时间间隔

        ExponentialBackOff backOff = new ExponentialBackOff(initialInterval, multiplier);
        backOff.setMaxInterval(maxInterval);
        backOff.setMaxElapsedTime(maxElapsedTime);

        BackOffExecution execution = backOff.start();

        while (true) {
            long value = execution.nextBackOff();
            if (value == BackOffExecution.STOP) {
                System.out.println("ExponentialBackOff执行中止");
                break;
            } else {
                try {
                    Thread.sleep(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ExponentialBackOff执行了一次:" + value);
            }
        }
    }
}
