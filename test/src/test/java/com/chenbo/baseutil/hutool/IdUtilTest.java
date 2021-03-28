package com.chenbo.baseutil.hutool;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2021-03-28
 */
@SpringBootTest
public class IdUtilTest {
    @Test
    public void test() {
        System.out.println(IdUtil.fastSimpleUUID());
        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.randomUUID());
        System.out.println(IdUtil.simpleUUID());
    }
}
