package com.zookao;

import com.zookao.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class UserServiceTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("master.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.getList();
    }

    @Test
    public void test12(){
        ApplicationContext context = new ClassPathXmlApplicationContext("master.xml");
        UserService userService = (UserService) context.getBean("userServiceConstructor");
        userService.getList();
    }
}
