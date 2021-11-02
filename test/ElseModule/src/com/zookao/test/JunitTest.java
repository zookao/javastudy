package com.zookao.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * User: czc
 * Date: 2021-09-24
 */
public class JunitTest {
    @Test
    public void testEquals(){
        String s1 = "hello world";
        String s2 = "hello world";
        System.out.println(s1.equals(s2));
    }

    /*@Test
    public void singleTest() {
        Single instance = Single.getInstance();
        Single instance2 = Single.getInstance();
        System.out.println(instance == instance2);
    }*/

    @Test
    public void tryTest(){
        String str = "123";
        str = "abc";
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

    @Test
    public void test1(){
        int i = 1;
        switch (Status.getStatus(i)){
            case ACTIVE:
                System.out.println(1);
                break;
            case INACTIVE:
                System.out.println(0);
                break;
        }
    }

    @Test
    public void test2(){
        int sum = 0;
        outer: for (int i = 1; i < 100; i++) {
            inner: for (int j = 1; j < 50; j++) {
                if(j % 3 == 0){
                    continue outer;
                }
                sum += (i * j);
            }
        }
        System.out.println(sum);
    }

    @Test
    public void test3(){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            int v = (int)(Math.random() * 100);
            boolean add = set.add(v);
            if(!add){
                System.out.println("添加失败："+v);
            }
        }
        for (Integer i : set) {
            System.out.println(i);
        }
    }

    @Test
    public void test4(){
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        int[] ints1 = Arrays.copyOf(ints, ints.length);
        System.out.println(Arrays.toString(ints1));
    }

    @Test
    public void test5(){
        //取最大值
        int[] ints = {3, 5, 1, 4, 7, 6, 2};
        //方式一
        Arrays.sort(ints);
        System.out.println(ints[ints.length - 1]);
        //方式二
        int max = ints[0];
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] > max){
                max = ints[i];
            }
        }
        System.out.println(max);
        //方式三
        int asInt = Arrays.stream(ints).max().getAsInt();
        System.out.println(asInt);
    }
}

enum Status{
    ACTIVE(1),
    INACTIVE(0);
    int value;

    Status(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "value=" + value +
                '}';
    }

    public static Status getStatus(int status){
        for (Status value : Status.values()) {
            if(value.value == status){
                return value;
            }
        }
        return null;
    }
}