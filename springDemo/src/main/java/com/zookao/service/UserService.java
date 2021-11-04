package com.zookao.service;

import com.zookao.pojo.User;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public interface UserService {
    public void insert(User user);
    public List<User> getList();
}
