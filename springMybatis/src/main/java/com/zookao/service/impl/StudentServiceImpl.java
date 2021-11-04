package com.zookao.service.impl;

import com.zookao.dao.StudentDao;
import com.zookao.pojo.Student;
import com.zookao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-04
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int i = studentDao.insertStudent(student);
        return i;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
