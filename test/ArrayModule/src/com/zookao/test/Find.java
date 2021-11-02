package com.zookao.test;

/**
 * User: czc
 * Date: 2021-10-15
 */
public class Find {
    /**
     * 使用递归的二分查找
     *title:recursionBinarySearch
     *@param arr 有序数组
     *@param key 待查找关键字
     *@return 找到的位置
     */
    public static int recursionBinarySearch(int[] arr,int key,int low,int high){

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        int middle = (low + high) / 2; //初始中间位置
        if(arr[middle] > key){
            return recursionBinarySearch(arr, key, low, middle - 1);
        }else if(arr[middle] < key){
            return recursionBinarySearch(arr, key, middle + 1, high);
        }else {
            return middle;
        }
    }
}
