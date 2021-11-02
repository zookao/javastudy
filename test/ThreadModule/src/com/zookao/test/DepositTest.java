package com.zookao.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: czc
 * Date: 2021-09-27
 */
public class DepositTest {
    public static void main(String[] args) {
        Account account = new Account();
        Customer customer = new Customer(account);
        Thread t1 = new Thread(customer);
        Thread t2 = new Thread(customer);

        t1.setName("老公");
        t2.setName("老婆");

        t1.start();
        t2.start();
    }
}

class Account {
    private double money = 3000;
    ReentrantLock lock = new ReentrantLock();

    public void increase(double money) {
        lock.lock();
        if (money > 0) {
            this.money += money;
            System.out.println(Thread.currentThread().getName() + "存钱成功：" + money + "，账户余额：" + this.money);
        }
        lock.unlock();
    }

    public void decrease(double money) {
        lock.lock();
        if (money > 0 && this.money >= money) {
            this.money -= money;
            System.out.println(Thread.currentThread().getName() + "取钱成功：" + money + "，账户余额：" + this.money);
        }
        lock.unlock();
    }
}

class Customer implements Runnable {

    private Account account;

    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.account.increase(1000);
        }
    }
}