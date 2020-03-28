package com.gary.algorithmlib.interview.ByteDance;


/**
 * reference to :https://blog.csdn.net/qq_39404258/article/details/100776258
 *
 * [LeetCode]25进阶题：给出一个链表，每k个节点一组进行翻转，并返回翻转后的链表。
 *
 *
 * 给定一个单链表的头节点 head,实现一个调整单链表的函数，使得：
 * 每K个节点之间为一组进行逆序，并且从链表的尾部开始组起，头部剩余节点数量不够一组的不需要逆序。
 *
 * 说明：
 * 不能使用队列或者栈作为辅助
 *
 *
 * 例如：
 * 链表:1->2->3->4->5->6->7->8->null, K = 3。那么 6->7->8，3->4->5，1->2各位一组。 *
 * 调整后：1->2->5->4->3->8->7->6->null。其中 1，2不调整，因为不够一组。
 *
 */
public class LeetCode25NewVersion {
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

    /*
     * k个为一组逆序
     */
    private static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        //将前k个作为一个临时链表存起来用于接下来反转操作
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        //判断节点的数量是否能够凑成一组
        if (temp == null)
            return head;

        ListNode t2 = temp.next;
        temp.next = null;
        //把当前的组进行逆序
        ListNode newHead = reserve(head);
        //把之后的节点进行分组逆序
        ListNode newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;

        return newHead;
    }

    /*
     * 链表逆序
     */
    private static ListNode reserve(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reserve(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }


    public static ListNode solve(ListNode head, int k) {
        // 调用逆序函数
        head = reserve(head);
        // 调用每 k 个为一组的逆序函数（从头部开始组起）
        head = reverseKGroup(head, k);
        // 在逆序一次
        head = reserve(head);
        return head;
    }

    public static void main(String[] args) {
        //solve(headnode,3);
    }
}
