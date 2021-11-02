package com.zookao.test;

import java.util.Arrays;

/**
 * User: czc
 * Date: 2021-09-28
 * 数组扩容
 */
public class AddLength {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        array = Arrays.copyOf(array,array.length * 2);
        System.out.println(Arrays.toString(array));
    }
}
