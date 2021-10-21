package com.github.wugenshui.baseutil.baseutil.data.structure;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        binaryTree.infixOrder(binaryTree.root);
        binaryTree.preOrder(binaryTree.root);
        binaryTree.postOrder(binaryTree.root);
    }

    /**
     * 二叉树
     */
    public class BinaryTree implements Tree {

        Node root;

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
            if (root == null) {//当前树为空树，没有任何节点
                root = newNode;
                return true;
            } else {
                Node current = root;
                Node parentNode = null;
                while (current != null) {
                    parentNode = current;
                    if (current.data > key) {//当前值比插入值大，搜索左子节点
                        current = current.leftChild;
                        if (current == null) {//左子节点为空，直接将新值插入到该节点
                            parentNode.leftChild = newNode;
                            return true;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {//右子节点为空，直接将新值插入到该节点
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
            //查找删除值，找不到直接返回false
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
            //如果当前节点没有子节点
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

        // 中序遍历
        public void infixOrder(Node current) {
            if (current != null) {
                infixOrder(current.leftChild);
                System.out.print(current.data + " ");
                infixOrder(current.rightChild);
            }
        }

        // 前序遍历
        public void preOrder(Node current) {
            if (current != null) {
                System.out.print(current.data + " ");
                preOrder(current.leftChild);
                preOrder(current.rightChild);
            }
        }

        // 后序遍历
        public void postOrder(Node current) {
            if (current != null) {
                postOrder(current.leftChild);
                postOrder(current.rightChild);
                System.out.print(current.data + " ");
            }
        }

        // 找到最大值
        public Node findMax() {
            Node current = root;
            Node maxNode = current;
            while (current != null) {
                maxNode = current;
                current = current.rightChild;
            }
            return maxNode;
        }

        // 找到最小值
        public Node findMin() {
            Node current = root;
            Node minNode = current;
            while (current != null) {
                minNode = current;
                current = current.leftChild;
            }
            return minNode;
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
        private int data;
        // 左子节点的引用
        private Node leftChild;
        // 右子节点的引用
        private Node rightChild;

        // 打印节点内容
        public void display() {
            System.out.println(data);
        }
    }

    public interface Tree {
        // 查找节点
        public Node find(int key);

        // 插入新节点
        public boolean insert(int key);

        // 删除节点
        public boolean delete(int key);
    }
}
