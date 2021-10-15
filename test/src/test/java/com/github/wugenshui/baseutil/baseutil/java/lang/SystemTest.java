package com.github.wugenshui.baseutil.baseutil.java.lang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class SystemTest {
    /**
     * 将 src 数组 第 srcPos 个位置开始的 length 个元素 拷贝至 destPos 数组的第 destPos 个位置
     */
    @Test
    public void arraycopyTest() {
        int[] src = {1, 2, 3};
        int srcPos = 0;
        int[] dest = {55, 44, 33, 22, 11};
        int destPos = 0;
        int length = 3;
        System.arraycopy(src, srcPos, dest, destPos, length);
        System.out.println(Arrays.toString(dest));

        int[] src2 = {1, 2, 3};
        int srcPos2 = 1;
        int[] dest2 = {55, 44, 33, 22, 11};
        int destPos2 = 1;
        int length2 = 2;
        System.arraycopy(src2, srcPos2, dest2, destPos2, length2);
        System.out.println(Arrays.toString(dest2));
    }
}
