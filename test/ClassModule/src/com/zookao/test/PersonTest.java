package com.zookao.test;

/**
 * Date: 2021-09-23
 * this关键字的使用
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(1);
        System.out.println(p1.getAge());
        System.out.println();
        Person p2 = new Person("Jerry",20);
        System.out.println(p2.getAge());
    }
}

class Person{

    private String name;
    private int age;

    public Person(){
        this.work();
    }

    public Person(String name){
        this();
        this.name = name;
    }

    public Person(int age){
        this();
        this.age = age;
    }

    public Person(String name,int age){
        this(name);
        // this(age); //Call to 'this()' must be first statement in constructor body
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void work(){
        System.out.println("working");
    }
}