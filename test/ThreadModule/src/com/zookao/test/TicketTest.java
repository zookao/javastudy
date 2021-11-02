package com.zookao.test;

/**
 * User: czc
 * Date: 2021-09-27
 *
 * Thread实现方法（二）steps：（推荐）
 * implements Runnable
 * 实现run方法
 * 实例对象
 * new Thread(对象).start()
 *
 * =======
 *
 * 线程安全
 * 1、同步监视器（同步代码块）
 * 2、同步方法
 */
public class TicketTest {
    public static void main(String[] args) {
        Ticket t = new Ticket();

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

class Ticket implements Runnable{

    private int ticket = 100;

    /*Object object = new Object();
    @Override
    public void run() {
        while (true){
            //同步监视器
            synchronized (object){ // 同步代码块
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
            }
        }
    }*/

    @Override
    public void run() {
        while(true){
            show();
        }
    }
    //同步方法
    private synchronized void show(){
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖票，票号："+ticket);
            ticket--;
        }
    }
}