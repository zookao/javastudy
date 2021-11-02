package com.zookao.test;

public class CustomerTest {
    public static void main(String[] args) {
        Customer cust = new Customer("Jane", "Smith");

        Account acct = new Account(1000, 2000, 0.0123);

        cust.setAccount(acct);

        cust.getAccount().deposit(100);
        cust.getAccount().withdraw(960);
        cust.getAccount().withdraw(2000);
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private Account account;

    public Customer(String f,String l){
        this.firstName = f;
        this.lastName = l;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class Account {
    private int id;
    private double balance;
    private double annualInterestRate;

    public Account (int id, double balance, double annualInterestRate ){
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void withdraw (double amount){//取钱
        if(balance < amount){
            System.out.println("余额不足，取款失败");
            return;
        }
        balance -= amount;
        System.out.println("成功取出：" + amount);
    }

    public void deposit (double amount){
        if(amount > 0){
            balance += amount;
            System.out.println("成功存入：" + amount);
        }
    }
}
