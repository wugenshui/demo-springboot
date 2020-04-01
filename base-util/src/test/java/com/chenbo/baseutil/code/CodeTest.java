package com.chenbo.baseutil.code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * @author : chenbo
 * @date : 2020-04-01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CodeTest {
    @Test
    public void deCode() throws UnsupportedEncodingException {
        String str = "娌℃湁閭ｄ釜鏂囦欢鎴栫洰褰�";

        SortedMap<String, Charset> map = Charset.availableCharsets();
        List<String> list = new ArrayList<>();
        //for (String alias : map.keySet()) {
        //    // 输出字符集的别名
        //    list.add(alias);
        //}
        list.add("UTF-8");
        list.add("UTF-16");
        list.add("GBK");
        list.add("GB2312");
        list.add("ASCII");
        list.add("ISO-8859-1");

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String result = new String(str.getBytes(list.get(i)), list.get(j));
                System.out.println(list.get(i) + "->" + list.get(j) + ":" + result);
            }
        }

    }
}
