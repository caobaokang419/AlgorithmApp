package com.gary.algorithm.sort;

/**
 * @author gary
 * 排序算法：希尔排序
 */
public class ShellSort {
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2; //增量默认值，可定义为length/2
        while (gap > 0) {
            for (int i = gap; i < len; i++) { //这个循环里其实就是一个插入排序
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;//增量每次减半
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 2, 9, 8, 6, 7, 0, 3, 5};
        System.out.println(shellSort(a));
    }
}
