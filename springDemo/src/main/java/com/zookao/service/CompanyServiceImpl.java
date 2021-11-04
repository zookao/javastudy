package com.zookao.service;

import com.zookao.dao.CompanyDao;
import com.zookao.pojo.Company;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-03
 */
public class CompanyServiceImpl implements CompanyService{
    private CompanyDao companyDao;

    public CompanyServiceImpl() {
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getList() {
        return companyDao.getList();
    }
}
