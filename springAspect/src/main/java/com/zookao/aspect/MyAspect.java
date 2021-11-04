package com.zookao.aspect;

import com.zookao.pojo.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * User: zookao
 * Date: 2021-11-04
 */

/**
 * @Aspect 是aspectJ框架中的注解
 * 作用：表示当前类是切面类
 * 切面类；用来给业务方法增加功能的类
 */
@Component
@Aspect
public class MyAspect {
    /**
     * 定义方法：
     * 1、公共方法public
     * 2、方法没有返回值
     * 3、方法名称自定义
     * 4、参数可有可无，有参数时参数不是自定义的
     */
    @Before(value = "execution(public void com.zookao.service.impl.SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(JoinPoint jp){
        System.out.println(jp.getSignature());
        System.out.println(jp.getSignature().getName());
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("arg = " + arg);
        }
        System.out.println("=======前置通知，执行时间："+new Date());
    }

    // @Before(value = "execution(* *..*Impl.do*(..))")
    // public void myBefore2(){
    //     System.out.println("2=======前置通知，执行时间："+new Date());
    // }

    // @Before(value = "execution(* do*(..))")
    // public void myBefore3(){
    //     System.out.println("3=======前置通知，执行时间："+new Date());
    // }

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))",returning = "res")
    public void myAfterReturning1(Object res){
        System.out.println("=======后置通知，获取的返回值是："+res);
        if(res.equals("doOther")){
            System.out.println("1");
        }else{
            System.out.println("2");
        }
        if(res != null){
            res = "返回值不能被修改";
        }
    }

    @Around(value = "execution(* *..SomeServiceImpl.doAround(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        System.out.println("环绕通知，目标方法之前"+new Date());
        result = pjp.proceed(); //method.invoke
        System.out.println("环绕通知，目标方法之后"+new Date());
        if (result != null) {
            result = "返回值被我修改了！";
        }
        return result;
    }
}
