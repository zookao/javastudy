package com.zookao.boot.service.impl;

import com.zookao.boot.bean.Student;
import com.zookao.boot.mapper.StudentMapperOrigin;
import com.zookao.boot.service.StudentServiceOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-18
 */
@Service
public class StudentServiceOriginImpl implements StudentServiceOrigin {
    @Autowired
    private StudentMapperOrigin studentMapperOrigin;

    @Override
    public Student getStudentById(Long id) {
        return studentMapperOrigin.selectStudentById(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentMapperOrigin.selectStudents();
    }
}
