package com.gary.algorithmlib.common.bintree;

import java.util.ArrayList;
import java.util.List;


/**
 * @author gary
 * <p>
 * 1. 二叉树遍历（前、中、后三种方式）+递归&非递归两种算法实现
 * 2. 二叉树查找
 */
public class BinaryTree {
    /**
     * 二叉树节点
     */
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 遍历方式
     */
    enum Direct {
        MIDDLE, BEFORE, AFTER;
    }

    /**
     * 递归遍历
     */
    public static void recursiveTraversal(TreeNode TreeNode, Direct direct) {
        if (TreeNode != null) {
            if (direct == Direct.BEFORE) {
                System.out.print(TreeNode.value);
                recursiveTraversal(TreeNode.left, direct);
                recursiveTraversal(TreeNode.right, direct);
            } else if (direct == Direct.MIDDLE) {
                recursiveTraversal(TreeNode.left, direct);
                System.out.print(TreeNode.value);
                recursiveTraversal(TreeNode.right, direct);
            } else if (direct == Direct.AFTER) {
                recursiveTraversal(TreeNode.left, direct);
                recursiveTraversal(TreeNode.right, direct);
                System.out.print(TreeNode.value);
            }
        }
    }

    /**
     * 模拟栈的功能
     */
    public static class Stack<E> {
        public List<E> mTreeNodes = new ArrayList<>();

        /**
         * 把节点放入栈
         *
         * @param TreeNode
         */
        public void push(E TreeNode) {
            mTreeNodes.add(TreeNode);
        }

        /**
         * 从栈顶pop出一个节点并得到这个节点
         *
         * @return
         */
        public E pop() {
            if (mTreeNodes != null) {
                return mTreeNodes.remove(mTreeNodes.size() - 1);
            }

            return null;
        }

        /**
         * 判断栈里是否还有数据
         *
         * @return
         */
        public boolean isEmpty() {
            return mTreeNodes.size() == 0;
        }

        /**
         * 查看当前栈顶的元素
         *
         * @return
         */
        public E top() {
            return mTreeNodes.get(mTreeNodes.size() - 1);
        }
    }


    /**
     * 非递归: 前序遍历
     *
     * @param root
     */
    public static void preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode t = root;
        while (t != null || !stack.isEmpty()) {
            while (t != null) {
                System.out.print(t.value);
                stack.push(t);
                t = t.left;
            }
            if (!stack.isEmpty()) {
                t = stack.pop();
                t = t.right;
            }
        }
    }


    /**
     * 非递归: 中序遍历
     *
     * @param root
     */
    public static void inOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode t = root;
        while (t != null || !stack.isEmpty()) {
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
            if (!stack.isEmpty()) {
                t = stack.pop();
                System.out.println(t.value);
                t = t.right;
            }
        }
    }

    /**
     * 非递归: 后续遍历
     *
     * @param root
     */
    public static void postOrderTraversal(TreeNode root) {
        TreeNode cur;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            cur = stack.top();
            if ((cur.left == null && cur.right == null)
                    || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.println(cur.value);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 二分查找，最优时间复杂度OLog(n).
     */
    private TreeNode search(TreeNode x, int key) {
        if (x == null)
            return x;

        int cmp = key - x.value;
        if (cmp < 0){
            return search(x.left, key);}
        else if (cmp > 0){
            return search(x.right, key);}
        else{
            return x;}
    }

    public static void main(String[] args) {
        int[] a = {8, 2, 4, 5, 3, 10, 11, 6, 9};
    }
}
