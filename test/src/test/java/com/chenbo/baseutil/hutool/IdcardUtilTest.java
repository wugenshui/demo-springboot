package com.chenbo.baseutil.hutool;

import cn.hutool.core.util.IdcardUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2021-03-28
 */
@SpringBootTest
public class IdcardUtilTest {
    @Test
    public void test() {
        String idCard = "340826199305075238";
        System.out.println(IdcardUtil.isValidCard(idCard));
        System.out.println(IdcardUtil.isValidCard("340826199305075239"));
        System.out.println(IdcardUtil.getBirth(idCard));
        System.out.println(IdcardUtil.getBirthDate(idCard));
        System.out.println(IdcardUtil.getAgeByIdCard(idCard));
        System.out.println(IdcardUtil.getGenderByIdCard(idCard));
        System.out.println(IdcardUtil.getProvinceByIdCard(idCard));
    }
}
