package com.chenbo.baseutil.hutool;

import cn.hutool.core.util.ZipUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-07-14
 */
@Ignore
@SpringBootTest
public class ZipUtilTest {

    /**
     * 打包至当前目录
     */
    @Test
    public void zip() {
        ZipUtil.zip("C:/Users/chenbo/Desktop/1.txt");
    }

    @Test
    public void uzip() {
        ZipUtil.unzip("C:/Users/chenbo/Desktop/1.zip", "C:/Users/chenbo/Desktop/new");
    }
}
