package com.gary.leetcode;


/**
 * [LeetCode]24.合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class LeetCode21 {
    /**
     * Definition for singly-linked list.
     */
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方式1：递归实现
     */
    public ListNode recursiveMergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = recursiveMergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = recursiveMergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 方式2：非递归实现
     */
    public ListNode noRecursiveMergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1=l1;
        ListNode p2=l2;
        ListNode p3=new ListNode(0);
        ListNode p=p3;
        while (true) {
            if (p1 == null && p2 == null) {
                break;
            } else if (p1 != null && p2 == null) {
                p.next = p1;
                break;
            } else if (p1 == null && p2 != null) {
                p.next = p2;
                break;
            } else if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        return p3.next;
    }

    public static void main(String[] args) {

    }
}
