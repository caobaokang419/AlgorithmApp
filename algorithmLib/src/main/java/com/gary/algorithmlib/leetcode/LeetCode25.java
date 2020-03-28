package com.gary.algorithmlib.leetcode;


/**
 * [LeetCode]25. 给出一个链表，每k个节点一组进行翻转，并返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * reference to :https://www.jianshu.com/p/c9b423fe1f05
 *
 */
public class LeetCode25 {
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

    // 一组结点的链表转置函数
    private void reverse(ListNode header, ListNode tail) {
        ListNode p = null, q = null, r = null;
        p = header.next;
        q = p.next; // 主要是要改变 q 指针的指向
        r = q.next;
        header.next = tail;
        p.next = tail.next;
        while(true){
            q.next = p;
            p = q;
            if(p == tail) break;
            q = r;
            r = r.next;
        }
    }

    /**
     * 链表类问题做到现在，总结出了如下两点技巧：
     * 1.处理链表问题时，一般都给输入的链表加一个头结点，这样就可以在一个循环里统一处理所有的结点，而不需要去特殊处理原本的头节点。
     * 2.处理链表类问题，我觉得使用 while(true){} 这种死循环最顺手。因为在你写出循环体的代码之前，你很难立马准确的判断循环截止条件是什么，与其在写循环体的时候老是操心 while() 里的循环截止条件，边写循环体边把循环截止条件改来改去。还不如顺着循环体的思路一路顺畅的写下去，然后在需要跳出循环时，直接使用 if 语句判断是否应该跳出循环即可，而且提前跳出循环也能避免一些不必要的麻烦。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1){
            return head;
        }
        ListNode header = new ListNode(0);
        header.next = head;
        ListNode p = header;
        ListNode q = header;
        ListNode r = null;
        while(true){
            for(int i = 0; i < k; ++i){
                q = q.next;
                if(q == null) return header.next; // 后面的结点不够一组，不需要翻转，可以直接返回结果了
            }
            r = p.next;
            reverse(p, q);
            p = r;
            q = r;
        }
    }

    public static void main(String[] args) {

    }
}
