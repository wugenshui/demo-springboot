package com.chenbo.baseutil.hutool;

import cn.hutool.core.util.IdcardUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2021-03-28
 */
@SpringBootTest
public class IdcardUtilTest {
    @Test
    public void test() {
        List<String> idCards = new ArrayList() {{
            add("340825199305075792"); // 随机生成的身份证号
            add("340825199305079195");
            add("340825199305077157");
            add("34082519930507655X");
            add("340825199305075733");
            add("340825199305075734"); // 错误的
        }};

        idCards.forEach(idCard -> {
            System.out.println("号码：" + idCard);
            System.out.println("isValidCard=" + IdcardUtil.isValidCard(idCard));
            System.out.println("getBirth=" + IdcardUtil.getBirth(idCard));
            System.out.println("getBirthDate=" + IdcardUtil.getBirthDate(idCard));
            System.out.println("getAgeByIdCard=" + IdcardUtil.getAgeByIdCard(idCard));
            System.out.println("getGenderByIdCard=" + IdcardUtil.getGenderByIdCard(idCard));
            System.out.println("getProvinceByIdCard=" + IdcardUtil.getProvinceByIdCard(idCard));
        });

        System.out.println("港澳台:");
        System.out.println(IdcardUtil.isValidCard("C668668(9)"));
        System.out.println(Arrays.toString(IdcardUtil.isValidCard10("C668668(9)")));
        System.out.println(Arrays.toString(IdcardUtil.isValidCard10("H04888686")));
    }
}
