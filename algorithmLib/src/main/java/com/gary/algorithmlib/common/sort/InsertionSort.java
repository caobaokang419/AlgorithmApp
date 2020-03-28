package com.gary.algorithmlib.common.sort;

/**
 * @author gary
 * 排序算法：插入排序
 */
public class InsertionSort {
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1]; //待插入的元素
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) { //已排序数列段中，找到待插入元素的index位置并向后挪位
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current; //插入元素
        }
        return array;
    }


    public static void main(String[] args) {
        int[] a = {1,4,2,9,8,6,7,0,3,5};
        System.out.println(insertionSort(a));
    }
}
