package com.github.wugenshui.baseutil.baseutil.data.structure;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * TireTree 字典树,  快速找词、字符串分词、词语补全
 *
 * @author : chenbo
 * @date : 2021-10-27
 */
@SpringBootTest
public class TireTreeOld2Test {
    @Test
    public void apiTest() {
        TireTree tree = new TireTree();

        String[] strs = {"azAZ09_", "maiccc", "maid", "ma"};
        // String[] strs = {"a"};
        for (int i = 0, j = strs.length; i < j; i++) {
            tree.createTireTree(strs[i]);
        }

        String str2 = "ma";
        List<String> list2 = tree.wordFill(str2);
        System.out.println("list2 = " + list2);
    }
}

class TireTree {

    private TreeNode root = new TreeNode(true);

    // 在字典树中创建词子树
    public void createTireTree(String str) {
        TreeNode node = root;
        char[] chars = str.toCharArray();
        int loc = 0;

        for (int i = 0, j = chars.length; i < j; i++) {
            // 字符映射下标
            loc = getIndex(chars[i]);
            if (node.childs[loc] == null) {
                node.childs[loc] = new TreeNode(chars[i], i != chars.length);
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
        TreeNode node = root;
        // 找到字符串末字符在字典树中的位置
        for (int i = 0, j = chars.length; i < j; i++) {
            loc = getIndex(chars[i]);
            if (node.childs[loc] != null) {
                sb.append(chars[i]);
                node = node.childs[loc];
            } else {
                return null;
            }
        }

        String prefix = String.valueOf(sb);
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        node.str = prefix;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.remove();
            if (node.isEnd) {
                list.add(node.str);
            }
            if (node.childs != null) {
                for (int i = 0, j = node.childs.length; i < j; i++) {
                    if (node.childs[i] != null) {
                        node.childs[i].str = node.str + node.childs[i].data;
                        queue.add(node.childs[i]);
                    }
                }
            }
        }
        return list;
    }

    public int getIndex(char c) {
        int result = 0;
        if (c >= 'a' && c <= 'z') {
            result = c - 'a';
        } else if (c >= 'A' && c <= 'Z') {
            result = c - 'A' + 26;
        } else if (c >= '0' && c <= '9') {
            result = c - '0' + 52;
        } else {
            result = 62;
        }
        return result;
    }
}

class TreeNode {
    static final int MAX_SIZE = 63;
    String str;
    char data;
    TreeNode[] childs;
    boolean isEnd;

    public TreeNode(boolean initChild) {
        if (initChild) {
            this.childs = new TreeNode[MAX_SIZE];
        }
        this.isEnd = false;
    }

    public TreeNode(char ch, boolean initChild) {
        if (initChild) {
            this.childs = new TreeNode[MAX_SIZE];
        }
        this.data = ch;
        this.isEnd = false;
    }

    public void initChild() {
        this.childs = new TreeNode[MAX_SIZE];
    }
}
