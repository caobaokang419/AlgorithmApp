package com.gary.algorithm.bintree;


import java.util.LinkedList;

/**
 * @author gary
 *
 * 平衡二叉树（）
 *
 */
public class AvlBalanceTree<T extends Comparable<T>> {
    private class TreeNode<T> {
        T element;
        int height;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T e) {
            element = e;
        }

        boolean isBalanced() {
            int leftHeight = left == null ? -1 : left.height;
            int rightHeight = right == null ? -1 : right.height;
            return Math.abs(leftHeight - rightHeight) <= 1 ? true : false;
        }

        public String toString() {
            return "Element:" + element + " Height:" + height + " Balanced:" + isBalanced();
        }
    }

    private TreeNode<T> root;

    public AvlBalanceTree() {
    }

    /**
     * AVL节点的高度计算：
     */
    private int calHeight(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * AVL节点插入
     */
    public void insertNonRecursive(T e) {
        LinkedList<TreeNode<T>> trace = new LinkedList<>();
        insertNonRecursive0(e, trace);
        reBalanceAfterInsert(trace, e);
    }

    private void insertNonRecursive0(T e, LinkedList<TreeNode<T>> trace) {
        TreeNode<T> node = new TreeNode<>(e);
        if (root == null) {
            root = node;
            root.height = 0;
            return;
        }
        TreeNode<T> current = root;
        trace.push(current);
        int compareInt = 0;
        while (true) {
            compareInt = current.element.compareTo(e);
            if (compareInt > 0) {
                if (current.left == null) {
                    current.left = node;
                    trace.push(node);
                    return;
                }
                current = current.left;
                trace.push(current);
            } else if (compareInt < 0) {
                if (current.right == null) {
                    current.right = node;
                    trace.push(node);
                    return;
                }
                current = current.right;
                trace.push(current);
            } else {
                // 相等 不插入
                return;
            }
        }
    }

    private void reBalanceAfterInsert(LinkedList<TreeNode<T>> trace, T e) {
        TreeNode<T> unBalanceNode = null;
        TreeNode<T> unBalNodeParent = null;
        TreeNode<T> n = null;
        while ((n = trace.peek()) != null && unBalanceNode == null) {
            n = trace.pop();
            n.height = calHeight(n);
            if (!n.isBalanced()) {
                unBalanceNode = n;
            }
        }
        if (trace.peek() != null) {
            unBalNodeParent = trace.pop();
        }
        // 平衡状态 返回
        if (unBalanceNode == null) {
            return;
        }
        // 确定插入方式
        int compareInt = unBalanceNode.element.compareTo(e);
        if (compareInt > 0) {
            if (calHeight(unBalanceNode.left.left) - calHeight(unBalanceNode.left.right) > 0) {
                rotateRight(unBalanceNode, unBalNodeParent);
            } else {
                // 先左旋时，视原非平衡节点的左儿子为非平衡节点
                rotateLeft(unBalanceNode.left, unBalanceNode);
                rotateRight(unBalanceNode, unBalNodeParent);
            }
        } else if (compareInt < 0) {
            if (calHeight(unBalanceNode.right.left) - calHeight(unBalanceNode.right.right) < 0) {
                rotateLeft(unBalanceNode, unBalNodeParent);
            } else {
                rotateRight(unBalanceNode.right, unBalanceNode);
                rotateLeft(unBalanceNode, unBalNodeParent);
            }
        }
        // 旋转完毕，旋转子树的高度信息已经更新过了，继续更新非平衡节点以上的高度信息
        while ((n = trace.peek()) != null) {
            n = trace.pop();
            n.height = calHeight(n);
        }
    }

    /**
     * 右旋转
     */
    private void rotateRight(TreeNode<T> unBalanceNode, TreeNode<T> unBalNodeParent) {
        TreeNode<T> newRoot = unBalanceNode.left;
        TreeNode<T> ubParent = unBalNodeParent;
        // 周知树其余部分
        if (ubParent != null) {
            if (ubParent.left != null && ubParent.left.equals(unBalanceNode)) {
                ubParent.left = newRoot;
            } else {
                ubParent.right = newRoot;
            }
        } else {
            this.root = newRoot;
        }
        // 儿子移交
        if (newRoot.right != null) {
            unBalanceNode.left = newRoot.right;
        } else {
            unBalanceNode.left = null;
        }
        // 新根上任，左儿子无需处理
        newRoot.right = unBalanceNode;

        // 更新三个节点的高度信息
        unBalanceNode.height = calHeight(unBalanceNode);
        newRoot.height = calHeight(newRoot);
        if (ubParent != null) {
            ubParent.height = calHeight(ubParent);
        }
    }

    /**
     * 左旋转
     */
    private void rotateLeft(TreeNode<T> unBalanceNode, TreeNode<T> unBalNodeParent) {
        TreeNode<T> newRoot = unBalanceNode.right;
        TreeNode<T> ubParent = unBalNodeParent;

        if (ubParent != null) {
            if (ubParent.left != null && ubParent.left.equals(unBalanceNode)) {
                ubParent.left = newRoot;
            } else {
                ubParent.right = newRoot;
            }
        } else {
            this.root = newRoot;
        }

        if (newRoot.left != null) {
            unBalanceNode.right = newRoot.left;
        } else {
            unBalanceNode.right = null;
        }

        newRoot.left = unBalanceNode;

        unBalanceNode.height = calHeight(unBalanceNode);
        newRoot.height = calHeight(newRoot);
        if (ubParent != null) {
            ubParent.height = calHeight(ubParent);
        }
    }

    /**
     * 非递归方式删除
     */
    public void removeNonRecursive(T e) {
        LinkedList<TreeNode<T>> trace = new LinkedList<>();
        TreeNode<T> current = root;
        trace.push(current);
        // 尝试删除，该节点没有儿子或者只有一个儿子
        if (tryRemove(e, trace, current)) {
            // 更新节点高度信息，修正平衡状态
            // 此时栈顶为被删除节点的父节点或者空栈
            if (trace.isEmpty()) {
                return;
            }
            // 弹出栈，更新高度信息并检查平衡
            reBalanceAfterRemove(trace, e);
        } else {
            // 尝试失败，该节点有两个儿子，此时栈顶元素即为将要删除的节点A
            current = trace.peek();
            // 找到A的右子树中最小的节点B，该节点没有左儿子
            TreeNode<T> rMin = findMin(current.right);
            // 此时删除目标变为最小的右儿子B
            tryRemove(rMin.element, trace, current);
            reBalanceAfterRemove(trace, rMin.element);
            // 删除完成，将B的值替换到原来要删除的节点A上
            current.element = rMin.element;
        }
    }


    private void reBalanceAfterRemove(LinkedList<TreeNode<T>> trace, T e) {
        while (true) {
            TreeNode<T> n = null;
            TreeNode<T> unBalanceNode = null;
            TreeNode<T> unBalNodeParent = null;
            while ((n = trace.peek()) != null && unBalanceNode == null) {
                n = trace.pop();
                n.height = calHeight(n);
                if (!n.isBalanced()) {
                    unBalanceNode = n;
                }
            }

            // 平衡态
            if (unBalanceNode == null) {
                return;
            }

            unBalNodeParent = trace.peek();

            int compareInt = unBalanceNode.element.compareTo(e);

            // 被删除节点是不平衡节点的左儿子，则右子树高于左子树
            if (compareInt > 0) {
                // 如果不平衡节点的右儿子的左子树高于右子树，则需要先右后左双旋转（相当于右左插入）
                if (calHeight(unBalanceNode.right.left) - calHeight(unBalanceNode.right.right) > 0) {
                    rotateRight(unBalanceNode.right, unBalanceNode);
                    rotateLeft(unBalanceNode, unBalNodeParent);
                } else {
                    // 相等和小于的情况 都可以通过左旋平衡
                    rotateLeft(unBalanceNode, unBalNodeParent);
                }
            } else {
                // 相当于左右插入
                if (calHeight(unBalanceNode.left.left) - calHeight(unBalanceNode.left.right) < 0) {
                    rotateLeft(unBalanceNode.left, unBalanceNode);
                    rotateRight(unBalanceNode, unBalNodeParent);
                } else {
                    // 相等和大于的情况 都可以通过右旋平衡
                    rotateRight(unBalanceNode, unBalNodeParent);
                }
            }

            for (TreeNode<T> n1 : trace) {
                n1.height = calHeight(n1);
            }
        }
    }

    private boolean tryRemove(T e, LinkedList<TreeNode<T>> trace, TreeNode<T> current) {
        while (true) {
            int compareInt = current.element.compareTo(e);
            if (compareInt > 0) {
                if (current.left == null) {
                    // 没有找到节点 直接返回
                    return true;
                }
                current = current.left;
                trace.push(current);
            } else if (compareInt < 0) {
                if (current.right == null) {
                    // 没有找到节点
                    return true;
                }
                current = current.right;
                trace.push(current);
            } else {
                // 找到元素，情况一：该节点为叶子节点
                if (current.left == null && current.right == null) {
                    // 弹出被删除节点
                    trace.pop();
                    if (current == root) {
                        root = null;
                        return true;
                    }
                    TreeNode<T> parent = trace.peek();
                    // 查询父节点
                    if (parent.left == current) {
                        parent.left = null;
                        return true;
                    } else {
                        parent.right = null;
                        return true;
                    }
                }
                // 找到元素，情况二：该节点只有一个儿子

                if (current.left == null) {
                    trace.pop();// 弹出被删除节点
                    TreeNode<T> son = current.right;
                    if (current == root) {
                        root = son;
                        return true;
                    }
                    TreeNode<T> parent = trace.peek();
                    if (parent.left == current) {
                        parent.left = son;
                        return true;
                    } else {
                        parent.right = son;
                        return true;
                    }
                }

                if (current.right == null) {
                    trace.pop();// 弹出被删除节点
                    TreeNode<T> son = current.left;
                    if (current == root) {
                        root = son;
                        return true;
                    }
                    TreeNode<T> parent = trace.peek();
                    if (parent.left == current) {
                        parent.left = son;
                        return true;
                    } else {
                        parent.right = son;
                        return true;
                    }
                }
                // 找到节点，情况三：但是节点有两个儿子，返回false
                return false;
            }
        }
    }
}
