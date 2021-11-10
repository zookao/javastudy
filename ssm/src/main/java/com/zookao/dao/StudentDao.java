package com.zookao.dao;

import com.zookao.pojo.Student;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-10
 */
public interface StudentDao {
    public int insertStudent(Student student);
    public List<Student> selectStudents();
}
