package com.github.wugenshui.baseutil.baseutil.data.structure;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * TireTree 字典树,  快速找词、字符串分词、词语补全
 * 新版本，手动修改
 *
 * @author : chenbo
 * @date : 2021-10-27
 */
@SpringBootTest
public class TireTreeTest {
    @Test
    public void apiTest() {
        TireTree tree = new TireTree();

        String[] strs = {"azAZ09_", "maiccc", "maid", "ma", "tma"};
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
        char loc = 0;

        for (int i = 0, j = chars.length; i < j; i++) {
            // 字符映射下标
            loc = chars[i];
            if (node.subNode(loc) == null) {
                node.childs.add(new TreeNode(chars[i], i != chars.length));
            }
            node = node.subNode(loc);
        }
        node.isEnd = true;
    }

    // 单词补齐
    public List<String> wordFill(String str) {
        char[] chars = str.toCharArray();
        char loc = 0;

        List<String> list = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        TreeNode node = root;
        // 找到字符串末字符在字典树中的位置
        for (int i = 0, j = chars.length; i < j; i++) {
            loc = chars[i];
            if (node.subNode(loc) != null) {
                sb.append(chars[i]);
                node = node.subNode(loc);
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
                for (int i = 0, j = node.childs.size(); i < j; i++) {
                    if (node.childs.get(i) != null) {
                        node.childs.get(i).str = node.str + node.childs.get(i).data;
                        queue.add(node.childs.get(i));
                    }
                }
            }
        }
        return list;
    }
}

class TreeNode {
    String str;
    char data;
    LinkedList<TreeNode> childs;
    boolean isEnd;

    public TreeNode(boolean initChild) {
        if (initChild) {
            this.childs = new LinkedList<>();
        }
        this.isEnd = false;
    }

    public TreeNode(char ch, boolean initChild) {
        if (initChild) {
            this.childs = new LinkedList<>();
        }
        this.data = ch;
        this.isEnd = false;
    }

    public TreeNode subNode(char c) {
        if (childs != null) {
            for (TreeNode eachChild : childs) {
                if (eachChild.data == c) {
                    return eachChild;
                }
            }
        }
        return null;
    }
}

