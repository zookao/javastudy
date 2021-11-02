package com.zookao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: zookao
 * Date: 2021-10-27
 */
public class ProxyHandler implements InvocationHandler {

    private Object obj;

    public ProxyHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object value = method.invoke(obj, args);
        return value;
    }
}
