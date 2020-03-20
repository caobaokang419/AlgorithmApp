package com.gary.leetcode;


import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * [LeetCode]215 数组中的第 K 个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * */
public class LeetCode215 {

    /**
     * 方式1：直接排序完了取, 时间复杂度 O(logn)
     */
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 方式2：BFPRT 算法，时间复杂度：最坏O(N)
     */
    public static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = new int[arr.length];
        copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] tmp = new int[arr.length];
        for (int i = 0; i != arr.length; i++)
            tmp[i] = arr[i];
        return tmp;
    }

    public static int bfprt(int[] arr, int begin, int end, int i) {//begin到end范围内求第i小的数
        if (begin == end)
            return arr[begin];
        int pivot = medianOfMedians(arr, begin, end);//中位数作为划分值
        int[] pivotRange = partition(arr, begin, end, pivot);//进行划分，返回等于区域
        if (i >= pivotRange[0] && i <= pivotRange[1])
            return arr[i];
        else if (i < pivotRange[0])
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        else
            return bfprt(arr, pivotRange[1] + 1, end, i);
    }

    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int begin, int end) {
        Arrays.sort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j])
                    swap(arr, j - 1, j);
                else
                    break;
            }
        }
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue)
                swap(arr, ++small, cur++);
            else if (arr[cur] > pivotValue)
                swap(arr, cur, --big);
            else
                cur++;
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public int findKthLargest2(int[] nums, int k) {
        return getMinKthByBFPRT(nums, nums.length - k + 1);
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 2, 9, 8, 6, 7, 0, 3, 5};
    }
}
