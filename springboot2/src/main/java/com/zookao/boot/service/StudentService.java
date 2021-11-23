package com.zookao.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zookao.boot.bean.Student;

import java.util.Map;

/**
 * User: zookao
 * Date: 2021-11-18
 */
public interface StudentService extends IService<Student> {

    Map<String,String> login(Student student);
}
