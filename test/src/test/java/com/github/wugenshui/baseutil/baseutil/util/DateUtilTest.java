package com.github.wugenshui.baseutil.baseutil.util;

import com.github.wugenshui.baseutil.baseutil.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2019-12-07
 */
@SpringBootTest
class DateUtilTest {

    @Test
    public void formatDate() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2009-01-01");
        String formatDate = DateUtil.formatDate(date);
        Assert.assertEquals("时间格式化失败！", "2009-01-01", formatDate);
    }

    @Test
    public void formatDateString() {
    }

    @Test
    public void formatDatetime() {
    }

    @Test
    public void getDateFormat() {
    }

    @Test
    public void getDateTimeFormat() {
    }

    @Test
    public void countMinutes() {
    }

    @Test
    public void checkData() {
    }

    @Test
    public void formatByReg() {
    }
}