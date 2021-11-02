package com.zookao.test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Date: 2021-09-22
 * 排序
 */
public class Sort {
    public static void main(String[] args) {
        int[] a = new int[]{7,28,14,49,63,35};

        long begin = System.currentTimeMillis();
        // bubbleSort(a);
        // quickSort(a,0,a.length-1);
        ArrayList<Integer> quick = quick(a);
        System.out.println(Arrays.toString(quick.stream().mapToInt(Integer::valueOf).toArray()));
        long end = System.currentTimeMillis();
        // System.out.println(Arrays.toString(a));
        System.out.println("花费时间："+(end - begin));
    }

    /**
     * 冒泡排序
     * @param array
     */
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            while(array[j] >= base && i < j) {
                j--;
            }
            while(array[i] <= base && i < j) {
                i++;
            }
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static ArrayList<Integer> quick(int[] array){
        if(array.length > 0){
            int base = array[0];
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            for (int i : array) {
                if(i > base){
                    right.add(i);
                }
                if(i < base){
                    left.add(i);
                }
            }
            ArrayList<Integer> l = quick(left.stream().mapToInt(Integer::valueOf).toArray());
            ArrayList<Integer> r = quick(right.stream().mapToInt(Integer::valueOf).toArray());
            ArrayList<Integer> result = new ArrayList<>();
            if(l != null)
                result.addAll(l);
            result.add(base);
            if(r != null)
                result.addAll(r);
            return result;
        }
        return null;
    }
}
