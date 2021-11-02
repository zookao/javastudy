package com.zookao.test;

/**
 * User: czc
 * Date: 2021-09-26
 * Thread实现方法（一）steps：
 * 1继承extends Thread
 * 2重写run
 * 3实例子类对象
 * 4对象.start
 *方法：
 * currentThread()
 * getName()
 * setName()
 * yield()释放当前cpu
 * join()线程a调用线程b的join(),线程a进入阻塞状态，直到线程b结束，线程a才结束阻塞
 * stop() 已过时
 * sleep(long millitime)毫秒
 * isAlive
 * 优先级：1-10，默认5
 * getPriority()
 * setPriority()
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");
        MyThread mt = new MyThread();
        mt.setName("分线程一");
        mt.start();

        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+" 奇数：" + i);
            }
            if(i == 20){
                try {
                    mt.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+" 偶数：" + i);
            }
            if(i % 20 == 0){
                yield();
            }
        }
    }
}