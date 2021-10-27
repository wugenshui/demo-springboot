package com.github.wugenshui.baseutil.baseutil.data.structure;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * TireTree 字典树,  快速找词、字符串分词、词语补全
 *
 * @author : chenbo
 * @date : 2021-10-27
 */
@SpringBootTest
public class TireTree2Test {
    @Test
    public void apiTest() {
        TireTree2 tree = new TireTree2();

        //String[] strs = {"public", "static", "void", "main", "maia", "maib", "maiccc", "NIHAO", "asb_wq", "webxr_z", "AZmai"};
        String[] strs = {"maiccc", "maid", "ma"};
        for (int i = 0, j = strs.length; i < j; i++) {
            tree.createTireTree(strs[i]);
        }

        String str2 = "ma";
        List<String> list2 = tree.wordFill(str2);
        System.out.println("list2 = " + list2);
    }
}

class TireTree2 {

    private TreeNode2 root = new TreeNode2();
    private static final char startChar = '0';

    // 在字典树中创建词子树
    public void createTireTree(String str) {
        TreeNode2 node = root;
        char[] chars = str.toCharArray();
        int loc = 0;

        for (int i = 0, j = chars.length; i < j; i++) {

            // 字符映射下标
            loc = chars[i] - startChar;
            if (node.childs[loc] == null) {
                node.childs[loc] = new TreeNode2(chars[i]);
            }
            node = node.childs[loc];
        }
        node.isEnd = true;
    }

    // 单词补齐
    public List<String> wordFill(String str) {
        char[] chars = str.toCharArray();
        int loc = 0;

        List<String> list = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();

        // 找到字符串末字符在字典树中的位置
        for (int i = 0, j = chars.length; i < j; i++) {
            loc = chars[i] - startChar;
            if (root.childs[loc] != null) {
                sb.append(chars[i]);
                root = root.childs[loc];
            } else {
                return null;
            }
        }

        // 子树将单词补齐
        scanFind(root, String.valueOf(sb), list);

        return list;
    }


    // 搜索子树
    public void scanFind(TreeNode2 node, String prefix, List<String> list) {
        if (node.isEnd) {
            list.add(prefix);
        }
        for (int i = 0, j = node.childs.length; i < j; i++) {
            if (node.childs[i] != null) {
                scanFind(node.childs[i], prefix + node.childs[i].data, list);
            }
        }
    }
}

class TreeNode2 {
    static final int MAX_SIZE = 75;
    char data;
    TreeNode2[] childs;
    boolean isEnd;

    public TreeNode2() {
        this.childs = new TreeNode2[MAX_SIZE];
        this.isEnd = false;
    }

    public TreeNode2(char ch) {
        this.data = ch;
        this.childs = new TreeNode2[MAX_SIZE];
        this.isEnd = false;
    }
}