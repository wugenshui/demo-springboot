package com.chenbo.baseutil.java.util;

import cn.hutool.core.date.DateTime;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
        System.out.println("date = " + date);

        DateTime dateTime = DateTime.now();
        System.out.println("dateTime = " + dateTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
    }
}
