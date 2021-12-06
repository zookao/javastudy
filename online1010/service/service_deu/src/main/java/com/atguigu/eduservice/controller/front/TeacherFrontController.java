package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/eduservice/teacherFront")
@RestController
public class TeacherFrontController {

    @Autowired
    EduTeacherService teacherService;
    @Autowired
    EduCourseService courseService;
    //分页查询讲师
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return R.ok().data(map);
    }
    //讲师详情功能
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable String id){
        //1 根据讲师id查询讲师基本信息
        EduTeacher byId = teacherService.getById(id);
        //2 根据讲师id查询课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher",byId).data("courseList",courseList);
    }

}
