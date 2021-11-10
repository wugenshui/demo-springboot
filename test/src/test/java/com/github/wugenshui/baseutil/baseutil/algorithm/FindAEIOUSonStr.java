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
        int result = 0;
        if (word.length() < 5) {
            return result;
        }
        String[] words = word.split("");
        for (int i = 0; i < words.length - 4; i++) {
            for (int j = i; j < words.length; j++) {
                if ("aeiou".indexOf(words[j]) == -1) {
                    break;
                }
                if (j - i >= 4) {
                    String temp = word.substring(i, j + 1);
                    if (temp.indexOf("a") > -1 && temp.indexOf("e") > -1 && temp.indexOf("i") > -1 && temp.indexOf("o") > -1 && temp.indexOf("u") > -1) {
                        result++;
                        // System.out.println("找到了：" + result + " str:" + temp);
                    }
                }
            }
        }

        return result;
    }
}
