package com.github.wugenshui.baseutil.baseutil.data.structure;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 自定义二叉树测试
 *
 * @author : chenbo
 * @date : 2021-10-21
 */
@SpringBootTest
public class BinaryTreeTest {
    @Test
    public void apiTest() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(3);
        binaryTree.insert(1);
        binaryTree.insert(5);
        binaryTree.insert(7);
        System.out.println(binaryTree);
        System.out.println("中序遍历");
        binaryTree.infixOrder(binaryTree.root);
        System.out.println("\n前序遍历");
        binaryTree.preOrder(binaryTree.root);
        System.out.println("\n后序遍历");
        binaryTree.postOrder(binaryTree.root);
        System.out.println("binaryTree.findMin() = " + binaryTree.findMin());
        Assert.assertEquals(1, binaryTree.findMin());
        System.out.println("binaryTree.findMax() = " + binaryTree.findMax());
        Assert.assertEquals(7, binaryTree.findMax());
        System.out.println("binaryTree.printFromTopToBottom() = " + binaryTree.printFromTopToBottom());
        TreePrintUtil.pirnt(binaryTree.root);
        System.out.println("-------");
        // 打印树
        binaryTree.insert(13);
        binaryTree.insert(12);
        binaryTree.insert(54);
        binaryTree.insert(2);
        binaryTree.insert(17);
        binaryTree.insert(4);
        binaryTree.insert(137);
        binaryTree.insert(127);
        binaryTree.insert(117);
        binaryTree.insert(0);
        binaryTree.insert(107);
        TreePrintUtil.pirnt(binaryTree.root);
    }

    /**
     * 二叉树
     */
    public class BinaryTree implements Tree {

        /**
         * 内部数据存储，根节点
         */
        private Node root;

        @Override
        public Node find(int key) {
            Node current = root;
            while (current != null) {
                if (current.data > key) {
                    // 当前值比查找值大，搜索左子树
                    current = current.leftChild;
                } else if (current.data < key) {
                    // 当前值比查找值小，搜索右子树
                    current = current.rightChild;
                } else {
                    return current;
                }
            }
            // 遍历完整个树没找到，返回null
            return null;
        }

        @Override
        public boolean insert(int key) {
            Node newNode = new Node(key);
            // 当前树为空树，没有任何节点
            if (root == null) {
                root = newNode;
                return true;
            } else {
                Node current = root;
                Node parentNode = null;
                while (current != null) {
                    parentNode = current;
                    // 当前值比插入值大，搜索左子节点
                    if (current.data > key) {
                        current = current.leftChild;
                        // 左子节点为空，直接将新值插入到该节点
                        if (current == null) {
                            parentNode.leftChild = newNode;
                            return true;
                        }
                    } else {
                        current = current.rightChild;
                        // 右子节点为空，直接将新值插入到该节点
                        if (current == null) {
                            parentNode.rightChild = newNode;
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public boolean delete(int key) {
            Node current = root;
            Node parent = root;
            boolean isLeftChild = false;
            // 查找删除值，找不到直接返回false
            while (current.data != key) {
                parent = current;
                if (current.data > key) {
                    isLeftChild = true;
                    current = current.leftChild;
                } else {
                    isLeftChild = false;
                    current = current.rightChild;
                }
                if (current == null) {
                    return false;
                }
            }
            // 如果当前节点没有子节点
            if (current.leftChild == null && current.rightChild == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                return true;
            }
            return false;
        }

        /**
         * 中序遍历:左子树 —> 根节点 —> 右子树
         *
         * @param current 当前节点
         */
        public void infixOrder(Node current) {
            if (current != null) {
                infixOrder(current.leftChild);
                System.out.print(current.data + " ");
                infixOrder(current.rightChild);
            }
        }

        /**
         * 前序遍历:根节点 —> 左子树 —> 右子树
         *
         * @param current 当前节点
         */
        public void preOrder(Node current) {
            if (current != null) {
                System.out.print(current.data + " ");
                preOrder(current.leftChild);
                preOrder(current.rightChild);
            }
        }

        /**
         * 后序遍历:左子树 —> 右子树 —> 根节点
         *
         * @param current 当前节点
         */
        public void postOrder(Node current) {
            if (current != null) {
                postOrder(current.leftChild);
                postOrder(current.rightChild);
                System.out.print(current.data + " ");
            }
        }

        /**
         * 找到最大值
         *
         * @return 最大节点值
         */
        public int findMax() {
            Node current = root;
            Node maxNode = current;
            while (current != null) {
                maxNode = current;
                current = current.rightChild;
            }
            return maxNode.data;
        }


        /**
         * 找到最小值
         *
         * @return 最小节点值
         */
        public int findMin() {
            Node current = root;
            Node minNode = current;
            while (current != null) {
                minNode = current;
                current = current.leftChild;
            }
            return minNode.data;
        }

        @Override
        public String toString() {
            return root.toString();
        }

        public ArrayList<Integer> printFromTopToBottom() {
            ArrayList<Integer> list = new ArrayList<>();
            if (root == null) {
                return null;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node treeNode = queue.poll();
                if (treeNode.leftChild != null) {
                    queue.offer(treeNode.leftChild);
                }
                if (treeNode.rightChild != null) {
                    queue.offer(treeNode.rightChild);
                }
                list.add(treeNode.data);
            }
            return list;
        }
    }

    /**
     * 节点
     */
    public class Node {
        Node(int data) {
            this.data = data;
        }

        // 节点数据
        public int data;
        // 左子节点的引用
        public Node leftChild;
        // 右子节点的引用
        public Node rightChild;

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    /**
     * 树接口
     */
    public interface Tree {
        // 查找节点
        Node find(int key);

        // 插入新节点
        boolean insert(int key);

        // 删除节点
        boolean delete(int key);
    }
}
