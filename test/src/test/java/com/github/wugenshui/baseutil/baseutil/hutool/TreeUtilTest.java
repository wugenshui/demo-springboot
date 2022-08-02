package com.github.wugenshui.baseutil.baseutil.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@SpringBootTest
public class TreeUtilTest {
    @Test
    public void treeBuildTest() throws JsonProcessingException {
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 2));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 221));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 21));
        nodeList.add(new TreeNode<>("1", "0", "系统管理", 1));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 11));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 111));

        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");

        for (int i = 0; i < treeList.size(); i++) {
            Tree<String> rootNode = treeList.get(i);
            System.out.println(rootNode.getName());
            List<Tree<String>> rootNodeChildren = rootNode.getChildren();
            for (int j = 0; j < rootNodeChildren.size(); j++) {
                Tree<String> secondNode = rootNodeChildren.get(j);
                System.out.println("-" + secondNode.getName());
                List<Tree<String>> secondNodeChildren = secondNode.getChildren();
                if (CollUtil.isNotEmpty(secondNodeChildren)) {
                    for (int z = 0; z < secondNodeChildren.size(); z++) {
                        Tree<String> lastNode = secondNodeChildren.get(z);
                        System.out.println("--" + lastNode.getName());
                    }
                }
            }
        }

        System.out.println(new ObjectMapper().writeValueAsString(treeList));
    }
}
