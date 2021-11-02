package com.zookao.test;

import java.util.Arrays;

public class Base {
    public static void main(String[] args) {
        String[] a = new String[5];

        Bank bank = new Bank();
        bank.addCustomers("张","三");
        bank.addCustomers("李","四");
        bank.printCustomers();
    }
}

class Bank {
    private Customer[] customers = new Customer[10];
    private int count;

    public void addCustomers(String firstName,String lastName){
        Customer c = new Customer(firstName,lastName);
        customers[count++] = c;
    }

    public void printCustomers(){
        System.out.println(Arrays.toString(customers));
    }
}

class Customer {
    private String firstName;
    private String lastName;

    public Customer(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}