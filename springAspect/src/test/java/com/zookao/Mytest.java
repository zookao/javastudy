package com.zookao;

import com.zookao.pojo.Student;
import com.zookao.service.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: zookao
 * Date: 2021-11-04
 */
public class Mytest {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService someService = (SomeService) context.getBean("someServiceImpl");
        someService.doSome("zookao",18);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService someService = (SomeService) context.getBean("someServiceImpl");
        String ret = someService.doOther("zookao", 18);
        System.out.println(ret);
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService someService = (SomeService) context.getBean("someServiceImpl");
        String ret = someService.doAround("zookao", 18);
        System.out.println(ret);
    }
}
