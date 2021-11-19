package com.zookao.boot.service;

import com.zookao.boot.bean.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-18
 */
public interface StudentService {
    public Student getStudentById(Long id);
    public List<Student> getStudents();
}
