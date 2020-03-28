package com.gary.algorithmlib.leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * [LeetCode]384 打乱一个没有重复元素的数组。
 *
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();*/
public class LeetCode384 {
    /**
     * Returns a random shuffling of the array.
     */
    public static int[] shuffle(int[] src) {
        int[] result = src.clone();
        Random ran = new Random();
        int t;
        for (int i = result.length - 1; i > 0; i--) {
            int x = ran.nextInt(i + 1);
            t = result[i];
            result[i] = result[x];
            result[x] = t;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] a = {1, 4, 2, 9, 8, 6, 7, 0, 3, 5};
        System.out.println(shuffle(a));
    }
}
