package boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: zookao
 * Date: 2021-11-15
 */
// @Component
@ConfigurationProperties(prefix = "teacher")
public class Teacher {
    private String name;
    private Integer age;

    public Teacher() {
    }

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
        return "Teacher{name='" + name + '\'' + ", age=" + age + '}';
    }
}
