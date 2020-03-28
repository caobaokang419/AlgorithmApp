package com.gary.algorithmlib.common.sort;

/**
 * @author gary
 * 排序算法：选择排序
 */
public class SelectionSort {
    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j; //保存最小数的index
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }


    public static void main(String[] args) {
        int[] a = {1,4,2,9,8,6,7,0,3,5};
        System.out.println(selectionSort(a));
    }
}
