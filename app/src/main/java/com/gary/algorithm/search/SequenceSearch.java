package com.gary.algorithm.search;

/**
 * @author gary
 * 查找算法：顺序查找
 */
public class SequenceSearch {
    public static boolean sequenceSearch(int a[], int k, int value) {
        for (int i = 0; i < k; i++) {
            if (value == a[i])
                return true;
            else
                return false;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {8, 2, 4, 5, 3, 10, 11, 6, 9};
        System.out.println(sequenceSearch(a, a.length, 20));
    }
}