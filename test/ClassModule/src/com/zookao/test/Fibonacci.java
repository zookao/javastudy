package com.zookao.test;

/**
 * Date: 2021-09-22
 * 递归
 */
public class Fibonacci {
    public static void main(String[] args) {
        int sum = fibonacci(10);
        System.out.println("sum = " + sum);
    }

    public static int fibonacci(int a) {
        int sum = 0;
        if(a == 0){
            sum = 1;
        }else if(a == 1){
            sum = 4;
        }else if(a > 1){
            sum = 2 * fibonacci(a - 1) + fibonacci(a - 2);
        }
        return sum;
    }
}
