package com.zookao.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: zookao
 * Date: 2021-11-03
 */
@Component("student")
//@Component 不指定名称，默认为类名小写，比如放在pojo类上
//@Repository 放在Dao类上
//@Service 放在Service类上
//@Controller 放在控制器类上
public class Student {
    @Value("zookao")
    private String name;
    @Value("18")
    private Integer age;

    //autowired默认使用byType
    @Autowired(required = false) //找不到studyPlace是不是报错并终止运行，默认true，为false不终止运行
    @Qualifier("studyPlace") //使用byName
    //@Resource 先使用byName，byName失败后再使用byType
    //@Resource(name="studyPlace")只使用byName
    private School school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + '\'' + ", age=" + age + ", school=" + school + '}';
    }
}
