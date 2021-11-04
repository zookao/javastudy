package com.zookao.service;

import com.zookao.dao.UserDao;
import com.zookao.pojo.User;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public List<User> getList() {
        return userDao.getList();
    }
}
