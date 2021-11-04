package com.zookao.dao;

import com.zookao.pojo.User;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public interface UserDao {
    public void insert(User user);
    public List<User> getList();
}
