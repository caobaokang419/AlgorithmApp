package com.gary.algorithmlib.common.search;

/**
 * @author gary
 * 查找算法：折半查找
 */
public class BinarySearch {
    public static int binarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value < a[mid])
                high = mid - 1;
            if (value > a[mid])
                low = mid + 1;
            if (value == a[mid])
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[] a = {1,4,2,9,8,6,7,0,3,5}
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(a, a.length, 7));
    }
}