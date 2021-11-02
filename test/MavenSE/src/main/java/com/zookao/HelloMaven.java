package com.zookao;

/**
 * User: zookao
 * Date: 2021-10-21
 */
public class HelloMaven {
    public int add(int m,int n){
        return m+n;
    }

    public static void main(String[] args) {
        HelloMaven helloMaven = new HelloMaven();
        int add = helloMaven.add(1, 2);
        System.out.println("add = " + add);
    }
}
