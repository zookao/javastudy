package com.zookao.test;

/**
 * User: czc
 * Date: 2021-09-24
 * 模板方法设计模式
 */
public class TemplateTest {
    public static void main(String[] args) {
        SubTemplate st = new SubTemplate();
        long t = st.time();
        System.out.println("花费时间：" + t);
    }
}

abstract class Template{
    public long time(){
        long begin = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        return (end - begin);
    }

    public abstract void code();
}

class SubTemplate extends Template{
    @Override
    public void code() {
        long sum = 1;
        for (int i = 1; i <= 20; i++) {
            sum = sum * i;
        }
        System.out.println("sum = " + sum);
    }
}