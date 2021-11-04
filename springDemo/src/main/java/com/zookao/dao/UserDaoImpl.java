package com.zookao.dao;

import com.zookao.pojo.User;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void insert(User user) {
        System.out.println("user入库成功");
    }

    @Override
    public List<User> getList() {
        System.out.println("获取user列表成功");
        return null;
    }
}
