package com.github.wugenshui.baseutil.baseutil.hutool;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@SpringBootTest
public class ThreadUtilTest {
    @Test
    public void concurrencyTest() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);

    @Test
    public void dateFormatTest() {
        Date date = new Date();
        String excepted = simpleDateFormat.format(date);
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(1000, () -> {
            // 测试的逻辑内容
            String actual = simpleDateFormat.format(date);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(excepted, actual);
            Console.log(actual);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }
}
