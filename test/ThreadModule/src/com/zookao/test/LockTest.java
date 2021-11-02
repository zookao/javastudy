package com.zookao.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: czc
 * Date: 2021-09-27
 */
public class LockTest {
    public static void main(String[] args) {
        Lock t = new Lock();

        Thread p1 = new Thread(t);
        Thread p2 = new Thread(t);
        Thread p3 = new Thread(t);

        p1.setName("线程1");
        p2.setName("线程2");
        p3.setName("线程3");

        p1.start();
        p2.start();
        p3.start();
    }
}

class Lock implements Runnable{

    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖票，票号："+ticket);
                    ticket--;
                }else{
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}