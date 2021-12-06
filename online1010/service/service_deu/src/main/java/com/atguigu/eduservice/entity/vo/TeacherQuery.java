package com.atguigu.eduservice.entity.vo;

import lombok.Data;

@Data
public class TeacherQuery { //前端查询教师数据
    private String name;//教师名称
    private Integer level;//教师头衔
    private String begin;//开始时间
    private String end;//结束时间
}
