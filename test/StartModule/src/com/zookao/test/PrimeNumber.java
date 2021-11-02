package com.zookao.test;

/**
 * 质数
 */
public class PrimeNumber {
    public static void main(String[] args) {
        boolean isFlag = true;
        for(int i = 2;i <= 100;i++){
            for(int j = 2;j < i;j++){
                if(i % j == 0){
                    isFlag = false;
                    break;
                }
            }
            if(isFlag == true){
                System.out.println(i);
            }
            isFlag = true;
        }
    }

    public static void main2(String[] args) {
        int count = 0;
        long start = System.currentTimeMillis();
        label:for(int i = 2;i <= 100000;i++){
            for(int j = 2;j <= Math.sqrt(i);j++){
                if(i % j == 0){
                    continue label;
                }
            }
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("质数的个数为：" + count);
        System.out.println("所花费的时间为：" + (end - start));
    }
}
