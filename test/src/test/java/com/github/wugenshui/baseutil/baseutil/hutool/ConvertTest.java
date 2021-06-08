package com.github.wugenshui.baseutil.baseutil.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@SpringBootTest
public class ConvertTest {
    @Test
    public void toStrTest() {
        int a = 1;
        System.out.println(Convert.toStr(a));
        System.out.println("" + a);

        long[] b = {1, 2, 3, 4, 5};
        System.out.println(Convert.toStr(b));
        System.out.println(Arrays.toString(b));
    }

    @Test
    public void toIntArrayTest() {
        String[] b = {"1", "2", "3", "4"};
        System.out.println(Arrays.toString(Convert.toIntArray(b)));

        long[] c = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(Convert.toIntArray(c)));
    }

    @Test
    public void otherTest() {
        // 半角转全角
        String a = "123456789";
        String sbc = Convert.toSBC(a);
        System.out.println(sbc);

        // 半角转全角
        String b = "１２３４５６７８９";
        String dbc = Convert.toDBC(b);
        System.out.println(dbc);

        // 编码转换
        String code = "我不是乱码";
        String result = Convert.convertCharset(code, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        Assert.assertEquals(raw, code);
        System.out.println("result = " + result);
        System.out.println("raw = " + raw);

        // 货币大写转换
        double money = 67556.32;
        String digitUppercase = Convert.digitToChinese(money);
        System.out.println("digitUppercase = " + digitUppercase);
    }
}
