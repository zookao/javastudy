package com.zookao.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zookao.boot.bean.Student;
import com.zookao.boot.mapper.StudentMapper;
import com.zookao.boot.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * User: zookao
 * Date: 2021-11-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

}
