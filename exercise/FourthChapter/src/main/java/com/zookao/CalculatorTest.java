package com.zookao;

import java.lang.reflect.Proxy;

public class CalculatorTest {

	public static void main(String[] args) {
		//1.创建目标对象
    	Calculator calculator = new CalculatorImpl();    
    	
    	//2.创建调用处理器对象
    	ProxyHandler handler = new ProxyHandler(calculator);
    	
    	//3.动态生成代理对象
    	Calculator proxy = 
        		(Calculator)Proxy.newProxyInstance
        		  (CalculatorImpl.class.getClassLoader(),
        				  CalculatorImpl.class.getInterfaces(), handler); 
        
    	//4.客户端通过代理对象调用方法
        //本次调用将自动被代理处理器的invoke方法接收
    	int result = 0;
    	
    	result = proxy.add(1111111111, -1);   
    	System.out.println("The result is " + result);
    	
    	result = proxy.add(1111111111, 2000000000);   
    	System.out.println("The result is " + result);
	}
}
