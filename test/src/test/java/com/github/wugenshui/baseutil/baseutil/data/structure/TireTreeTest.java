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
    private TreeNode root = new TreeNode();

    // 在字典树中创建词子树
    public void createTireTree(String str) {
        TreeNode node = root;
        char[] chars = str.toCharArray();
        char loc = 0;
        for (int i = 0, j = chars.length; i < j; i++) {
            // 字符映射下标
            loc = chars[i];
            if (node.childs.get(loc) == null) {
                node.childs.put(loc, new TreeNode());
            }
            node = node.childs.get(loc);
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
            if (node.childs.get(loc) != null) {
                sb.append(chars[i]);
                node = node.childs.get(loc);
            } else {
                return null;
            }
        }

        String prefix = String.valueOf(sb);
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        node.str = prefix;
        queue.add(node);
        TreeNode tempTreeNode = null;
        while (!queue.isEmpty()) {
            node = queue.remove();
            if (node.isEnd) {
                list.add(node.str);
            }
            if (node.childs != null) {
                for (Map.Entry<Character, TreeNode> entry : node.childs.entrySet()) {
                    tempTreeNode = entry.getValue();
                    tempTreeNode.str = node.str + entry.getKey();
                    queue.add(tempTreeNode);
                }
            }
        }
        return list;
    }
}

class TreeNode {
    String str;
    Map<Character, TreeNode> childs;
    boolean isEnd;

    public TreeNode() {
        this.childs = new HashMap<>();
        this.isEnd = false;
    }
}
