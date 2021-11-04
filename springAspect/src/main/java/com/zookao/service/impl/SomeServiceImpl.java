package com.zookao.service.impl;

import com.zookao.pojo.Student;
import com.zookao.service.SomeService;
import org.springframework.stereotype.Service;

/**
 * User: zookao
 * Date: 2021-11-04
 */
@Service(value = "someServiceImpl")
public class SomeServiceImpl implements SomeService {

    @Override
    public void doSome(String name,Integer age) {
        //before
        System.out.println("=======before=======");
    }

    @Override
    public String doOther(String name, Integer age) {
        //after returning
        System.out.println("=======after returning=======");
        return "doOhter";

    }

    @Override
    public String doAround(String name, Integer age) {
        System.out.println("=======around=======");
        return "doAround";
    }
}
