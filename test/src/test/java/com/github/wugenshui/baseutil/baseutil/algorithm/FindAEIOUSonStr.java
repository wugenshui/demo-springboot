package com.github.wugenshui.baseutil.baseutil.algorithm;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 统计字符串中的元音子字符串
 * 子字符串 是字符串中的一个连续（非空）的字符序列。
 * 元音子字符串 是 仅 由元音（'a'、'e'、'i'、'o' 和 'u'）组成的一个子字符串，且必须包含 全部五种 元音。
 *
 * @author : chenbo
 * @date : 2021-11-10
 */
@SpringBootTest
public class FindAEIOUSonStr {
    @Test
    public void test() {
        Assert.assertEquals(4, countVowelSubstrings("aeiouua"));
        System.out.println("---------------");
        Assert.assertEquals(2, countVowelSubstrings("aeiouu"));
        System.out.println("---------------");
        Assert.assertEquals(0, countVowelSubstrings("unicornarihan"));
        System.out.println("---------------");
        Assert.assertEquals(7, countVowelSubstrings("cuaieuouac"));
        System.out.println("---------------");
        Assert.assertEquals(0, countVowelSubstrings("bbaeixoubb"));
        System.out.println("---------------");
        Assert.assertEquals(81, countVowelSubstrings("duuebuaeeeeeeuaoeiueaoui"));
        System.out.println("---------------");
    }

    public int countVowelSubstrings(String word) {
        int counter = 0;
        // 五个元音的状态
        int vowel = 1 | 1 << 4 | 1 << 8 | 1 << 14 | 1 << 20;
        for (int i = 0; i < word.length() - 4; i++) {
            int mask = 0;
            for (int j = i; j < word.length(); j++) {
                int temp = 1 << word.charAt(j) - 'a';
                // 碰到非元音结束内层循环
                if ((vowel & temp) == 0) {
                    break;
                }
                mask |= temp;
                if (vowel == mask) {
                    // System.out.println("找到了：" + result + " str:" + word.substring(i, j + 1));
                    counter++;
                }
            }
        }

        return counter;
    }
}
