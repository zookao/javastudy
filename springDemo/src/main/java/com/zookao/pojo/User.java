package com.zookao.pojo;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class User {
    private String username;
    private Integer age;
    private Company company;

    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{username='" + username + '\'' + ", age=" + age + ", company=" + company + '}';
    }
}
