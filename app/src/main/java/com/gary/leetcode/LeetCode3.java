package com.gary.leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [LeetCode] 3. Longest Substring Without Repeating Characters 最长无重复字符的子串
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
public class LeetCode3 {
    /**
     * 代码实现1（HashMap）：
     *
     * 每次出现重复的字符的时候，计算两个字符之间的长度，和上一次保存的长度取最大值
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) return 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);//每次更新了新出现从字符的位置
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * 代码实现2（HashSet）：
     *
     * 每次出现重复的字符的时候，计算两个字符之间的长度，和上一次保存的长度取最大值
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int max = 0, left = 0, right = 0;
        Set<Character> t = new HashSet<Character>();
        while (right < s.length()) {
            if (!t.contains(s.charAt(right))) {
                t.add(s.charAt(right++));
                max = Math.max(max, t.size());
            } else {
                t.remove(s.charAt(left++));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "adbcdabcaaccabcdedfadfafd";
        System.out.println(lengthOfLongestSubstring1(s));
    }
}
