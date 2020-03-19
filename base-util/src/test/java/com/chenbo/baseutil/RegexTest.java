package com.chenbo.baseutil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : chenbo
 * @date : 2020-03-19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
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
}
