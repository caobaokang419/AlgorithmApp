package com.gary.algorithmlib.common.sort;


/**
 * @author gary
 * 排序算法：冒泡排序
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        boolean swapFlag;
        for (int i = 0; i < array.length; i++){
            swapFlag= false;
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {//不断比较交换，实现最大值沉底（到数列尾部）
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    swapFlag= true;
                }
            if (!swapFlag) { //如遍历无swap，则直接return即可
                break;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        int[] a = {1,4,2,9,8,6,7,0,3,5};
        System.out.println(bubbleSort(a));
    }
}
