package com.zookao.boot.mapper;

import com.zookao.boot.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User: zookao
 * Date: 2021-11-18
 */
@Mapper
public interface StudentMapperOrigin {

    @Select("select id,name,email,age from student where id=#{id}")
    public Student selectStudentById(Long id);

    public List<Student> selectStudents();
}
