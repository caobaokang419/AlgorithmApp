package com.gary.algorithmlib.leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [LeetCode]24.两两交换相邻的节点
 * 描述：
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 说明:
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而且对于链表的操作，一般都要求操作节点而仅仅是修改节点上的数据。
 */
public class LeetCode24 {
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

    public ListNode swapPairs(ListNode head) {
        ListNode list1 = new ListNode(0);
        list1.next = head;
        ListNode list2 = list1;
        while (head != null && head.next != null) {
            list2.next = head.next;
            head.next = list2.next.next;
            list2.next.next = head;
            list2 = list2.next.next;
            head = list2.next;
        }
        return list1.next;
    }

    public static void main(String[] args) {

    }
}
