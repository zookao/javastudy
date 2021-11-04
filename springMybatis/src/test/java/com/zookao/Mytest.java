package com.zookao;

import com.zookao.dao.StudentDao;
import com.zookao.pojo.Student;
import com.zookao.service.StudentService;
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
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        Student s = new Student(0,"zookao", "zookao@126.com", 18);
        studentDao.insertStudent(s);
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) context.getBean("studentServiceImpl");
        Student s = new Student(0,"zookao1", "zookao@163.com", 19);
        service.addStudent(s);
    }
}
