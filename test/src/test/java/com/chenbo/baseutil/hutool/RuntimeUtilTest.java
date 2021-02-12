package com.chenbo.baseutil.hutool;

import cn.hutool.core.util.RuntimeUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@SpringBootTest
public class RuntimeUtilTest {
    @Test
    public void execTest() {
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println("str = " + str);
    }
}
