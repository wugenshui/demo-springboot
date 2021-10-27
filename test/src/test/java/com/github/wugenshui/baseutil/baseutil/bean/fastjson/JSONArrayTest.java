package com.github.wugenshui.baseutil.baseutil.bean.fastjson;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2021-10-27
 */
@SpringBootTest
public class JSONArrayTest {
    @Test
    public void apiTest() {
        JSONArray array = new JSONArray();
        array.add("张三");
        array.add("李四");
        System.out.println(array);
        for (Object obj : array) {
            System.out.println(obj.toString());
        }
    }
}
