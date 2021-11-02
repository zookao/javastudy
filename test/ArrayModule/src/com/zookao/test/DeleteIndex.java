package com.zookao.test;

import java.util.Arrays;

/**
 * User: czc
 * Date: 2021-09-23
 */
public class DeleteIndex {
    public static void main(String[] args) {
        String[] array = new String[5];
        array[0] = "0";
        array[1] = "1";
        array[3] = "3";
        deleteIndex(array,2);
        System.out.println(Arrays.toString(array));
    }

    public static void deleteIndex(String[] array,int index){
        if(index < 0 || index >= array.length){
            return;
        }
        for(int i = index;i < array.length - 1;i++){
            array[i] = array[i + 1];
        }
    }
}
