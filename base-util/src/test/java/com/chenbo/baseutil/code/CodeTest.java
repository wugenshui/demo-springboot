package com.chenbo.baseutil.code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CodeTest {
    @Test
    public void deCode() throws UnsupportedEncodingException {
        String str = "娌℃湁閭ｄ釜鏂囦欢鎴栫洰褰�"; // 没有那个文件或目录
        str = "鏉冮檺涓嶅\uE644"; // 权限不够
        str = "绗� 5 琛�:kill: (6592) - 娌℃湁閭ｄ釜杩涚▼"; // 没有那个进程

        List<String> list = new ArrayList<>();
        list.add("UTF-8");
        list.add("UTF-16");
        list.add("GBK");
        list.add("GB2312");
        list.add("ASCII");
        list.add("ISO-8859-1");

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    String result = new String(str.getBytes(list.get(i)), list.get(j));
                    System.out.println(list.get(i) + "编码->" + list.get(j) + "解码:" + result);
                }
            }
        }
    }
}
