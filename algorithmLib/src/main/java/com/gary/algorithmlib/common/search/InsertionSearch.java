package com.gary.algorithmlib.common.search;

/**
 * @author gary
 * 查找算法：插入查找
 */
public class InsertionSearch {
    public static int insertionSearch(int[] a, int value, int low, int high) {
        int mid = low + (value - a[low]) / (a[high] - a[low]) * (high - low);
        if (a[mid] == value)
            return mid;
        if (a[mid] > value)
            return insertionSearch(a, value, low, mid - 1);
        if (a[mid] < value)
            return insertionSearch(a, value, mid + 1, high);
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(insertionSearch(a, 2, 0, a.length - 1));
    }
}