package com.chenbo.baseutil.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-03-05
 */
@SpringBootTest
public class StringUtilsTest {

    @Test
    public void emptyTest() {
        boolean isBlank = StringUtils.isBlank("");
        System.out.println("[\"\"]为空" + isBlank);
        Assert.assertTrue(isBlank);

        isBlank = StringUtils.isBlank(null);
        System.out.println("null为空" + isBlank);
        Assert.assertTrue(isBlank);

        isBlank = StringUtils.isBlank(" ");
        System.out.println("[\" \"]为空" + isBlank);
        Assert.assertTrue(isBlank);
    }

    @Test
    public void trimTest() {
        String str = StringUtils.trim(" bob ");
        Assert.assertEquals("bob", str);
    }
}
