package com.zookao.boot.mapper;

import com.zookao.boot.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * User: zookao
 * Date: 2021-11-18
 */
@Mapper
public interface StudentMapper {
    public Student selectStudentById(Long id);
}
