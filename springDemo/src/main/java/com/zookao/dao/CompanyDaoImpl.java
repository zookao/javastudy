package com.zookao.dao;

import com.zookao.pojo.Company;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class CompanyDaoImpl implements CompanyDao{
    @Override
    public List<Company> getList() {
        System.out.println("获取company列表成功");
        return null;
    }
}
