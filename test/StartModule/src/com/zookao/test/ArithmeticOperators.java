package com.zookao.test;

public class ArithmeticOperators {
    public static void main(String[] args) {
        double d = 100.01;
        d += 10; // +=不会改变数据类型
        System.out.println(d);

        int i = 10;
        double dd = d + i;
        System.out.println(dd);
    }
}
