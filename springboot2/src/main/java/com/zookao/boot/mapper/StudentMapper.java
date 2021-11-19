package com.zookao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zookao.boot.bean.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: zookao
 * Date: 2021-11-19
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
