package com.zookao.service.impl;

import com.zookao.dao.StudentDao;
import com.zookao.pojo.Student;
import com.zookao.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-10
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        int i = studentDao.insertStudent(student);
        return i;
    }

    @Override
    public List<Student> findStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
