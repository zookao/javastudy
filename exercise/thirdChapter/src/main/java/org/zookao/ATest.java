package org.zookao;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Method;

/**
 * User: zookao
 * Date: 2021-10-27
 */
public class ATest {
    @Test
    public void test1() throws Exception {
        File file = new File("conf.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        String[] strings;
        while((line = bufferedReader.readLine()) != null){
            strings = line.split(",");
            String className = strings[0];
            String packageName = ATest.class.getPackage().getName();
            Class<?> clazz = Class.forName(packageName+"."+className);
            Method method = clazz.getDeclaredMethod(strings[1]);
            while(true){
                method.invoke(clazz.newInstance());
                Thread.sleep(5000);
            }
        }
    }
}
class A{
    public void f1(){
        System.out.println("hello java");
    }
}
