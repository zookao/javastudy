package com.zookao.dao;

import com.zookao.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-04
 */
@Repository
public interface StudentDao {
    public int insertStudent(Student student);
    public List<Student> selectStudents();
}
