package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@SpringBootTest
public class DateTest {
    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println("date = " + date.toLocaleString());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        date = calendar.getTime();
        System.out.println("date = " + date.toLocaleString());

        // 默认使用UTC时区
        Instant instant = Instant.now();
        System.out.println("instant:" + instant);
    }
}
