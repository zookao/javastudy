package com.zookao.service;

import com.zookao.pojo.Student;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-10
 */
public interface StudentService {
    public int addStudent(Student student);
    public List<Student> findStudents();
}
