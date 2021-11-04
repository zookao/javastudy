package com.zookao.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: zookao
 * Date: 2021-11-03
 */
@Component("studyPlace")
public class School {
    @Value("北京大学")
    private String name;
    @Value("海淀区")
    private String address;

    public School() {
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }
}
