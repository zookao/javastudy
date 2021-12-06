package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-09
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publishCoures", publishCourseInfo);
    }

    @PostMapping("updateStatus")
    public R updateStatus(@RequestBody CoursePublishVo coursePublishVo) {//修改课程发布状态
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(coursePublishVo.getId());
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id) {//根据课程id删除课程 章节 小节 描述
        eduVideoService.removeByCourseId(id);
        eduChapterService.removeByCourseId(id);
        eduCourseDescriptionService.removeById(id);
        eduCourseService.removeById(id);
        eduCourseService.removeById(id);
        return R.ok();
    }

    @PostMapping("getCourseQuery/{page}/{limit}") //分页+模糊查询课程
    public R getCourseQuery(@PathVariable("page") int page, @PathVariable("limit") int limit,
                            @RequestBody CourseQuery courseQuery) {
        Page pageRes = eduCourseService.getCourseQuery(page, limit, courseQuery);
        long total = pageRes.getTotal();
        List<EduCourse> records = pageRes.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }
}

