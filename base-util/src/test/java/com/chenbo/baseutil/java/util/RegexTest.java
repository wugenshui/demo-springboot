package com.chenbo.baseutil.java.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : chenbo
 * @date : 2020-03-19
 */
@SpringBootTest
public class RegexTest {

    @Test
    public void projectNameTest() {
        // pattern = "^([a-z]+-)+web|service|app|test|net$";
        String pattern = "([a-z]+-)+(test|sdms|net|web|app|service)";

        Map<String, Boolean> map = new HashMap<>();
        map.put("sdms-web", true);
        map.put("sdms-service", true);
        map.put("sdms-user-service", true);
        map.put("sdms-user-department-service", true);
        map.put("sdms-app", true);
        map.put("sdms-test", true);
        map.put("sdms-net", true);

        map.put("Sdms-web", false);
        map.put("sdms1-web", false);
        map.put("sd1ms-web", false);
        map.put("1sdms-web", false);
        map.put("-web", false);
        map.put("sdms-Service", false);
        map.put("sdms_app", false);
        map.put("sdms-test-user", false);
        map.put("sdms-net-", false);

        map.forEach((key, value) -> {
            boolean isMatch = Pattern.matches(pattern, key);
            Assert.assertEquals(key, value, isMatch);
            System.out.println((value ? " 成功" : " 失败") + " : " + key);
        });


    }

    @Test
    public void excludeTest() {
        // 不相符文本段匹配
        String pattern = "^application-((?!dev).)*$";

        Map<String, Boolean> map = new HashMap<>();
        map.put("application-dev.yml", false);
        map.put("application-test.yml", true);
        map.put("application-prod.yml", true);

        map.forEach((key, value) -> {
            boolean isMatch = Pattern.matches(pattern, key);
            Assert.assertEquals(key, value, isMatch);
            System.out.println((value ? " 匹配" : " 不匹配") + " : " + key);
        });
    }

    @Test
    public void versionTest() {
        Pattern versionRegex = Pattern.compile("^(\\d+)\\.(\\d+|x)\\.(\\d+|x)(?:[.-]([^0-9]+)(\\d+)?)?$");

        Map<String, Boolean> map = new HashMap<>();
        map.put("2.4.0.M1", true);
        map.put("2.0.1.RELEASE", true);
        map.put("2.4.0M1", false);
        map.put("2.4.0-SNAPSHOT", true);
        map.put("M1.2.3.4", false);

        map.forEach((key, value) -> {
            Matcher matcher = versionRegex.matcher(key);
            Assert.assertEquals(key, value, matcher.matches());
            System.out.println((value ? " 匹配" : " 不匹配") + " : " + key);
        });

    }
}
