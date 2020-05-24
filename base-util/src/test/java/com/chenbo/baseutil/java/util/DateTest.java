package com.chenbo.baseutil.java.util;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@SpringBootTest
public class DateTest {
    @Test
    public void bestTest() {
        LocalDateTime localDateTime = LocalDateTime.now();

        Date date = new Date();
        // Date to LocalDateTime
        localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);

        // LocalDateTime to Date
        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("date = " + date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(localDateTime));
    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println("date = " + date.toLocaleString());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        Calendar calendar = Calendar.getInstance();
        //calendar.set(2020, 1 - 1, 1);
        date = calendar.getTime();
        System.out.println("date = " + date.toLocaleString());

        // 默认使用UTC时区
        Instant instant = Instant.now();
        System.out.println("instant:" + instant);
    }

    @Test
    public void formatTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }

    /**
     * 验证DateFormat为线程不安全的测试
     */
    @Ignore
    @Test
    public void threadFormatExceptionTest() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (; ; ) {
            executorService.execute(() -> {
                Random random = new Random();
                try {
                    Date date = new Date(System.currentTimeMillis() + new Random().nextInt());
                    // format 方法存在多线程的问题
                    System.out.println(df.format(date));

                    // 使用第三方工具类 DateFormatUtils
                    //String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd'T'HH:mm:ss.SSS");

                    // 使用线程安全的类 DateTimeFormatter
                    //LocalDateTime localDateTime = LocalDateTime.parse(dateStr);
                    //df2.format(localDateTime);
                } catch (Exception e) {
                    e.printStackTrace();
                    executorService.shutdown();
                }
            });
        }
    }

    /**
     * 验证DateFormat为线程不安全的测试
     */
    @Ignore
    @Test(expected = RejectedExecutionException.class)
    public void threadParseExceptionTest() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (; ; ) {
            executorService.execute(() -> {
                try {
                    // parse 方法存在多线程的问题
                    df.parse("2017-06-10");
                } catch (Exception e) {
                    e.printStackTrace();
                    executorService.shutdown();
                }
            });
        }
    }

}
