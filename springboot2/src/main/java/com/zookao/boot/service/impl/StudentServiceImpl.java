package com.zookao.boot.service.impl;

import com.zookao.boot.bean.Student;
import com.zookao.boot.mapper.StudentMapper;
import com.zookao.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: zookao
 * Date: 2021-11-18
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(Long id) {
        return studentMapper.selectStudentById(id);
    }
}
