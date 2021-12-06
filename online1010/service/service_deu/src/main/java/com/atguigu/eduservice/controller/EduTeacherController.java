package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
@RestController //返回JSON数据
@RequestMapping("/eduservice/teacher") //请求路径
public class EduTeacherController {
    //注入service
    @Autowired
    EduTeacherService eduTeacherService;

    //查询所有讲师
    @GetMapping(value = "/findAll")
    public R findAllTeacher() {
        System.out.println("-------------");
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    //删除讲师
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) return R.ok();
        return R.error();
    }

    //分页查询
    @GetMapping(value = "pageTeacher/{current}/{limit}") //注意传递参数的写法
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        System.out.println("分页查询");
        Page<EduTeacher> page = new Page(current, limit);
        eduTeacherService.page(page, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    //分页+模糊查询
    @PostMapping(value = "pageQuery/{current}/{limit}") //参数中使用了@RequestBody注解 那么这里必须是post提交
    public R pageQuery(@PathVariable("current") long current, @PathVariable("limit") long limit,
                       @RequestBody TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper wrapper = new QueryWrapper();
        if (teacherQuery.getName() != null) wrapper.like("name", teacherQuery.getName());
        if (teacherQuery.getLevel() != null) wrapper.eq("level", teacherQuery.getLevel());
        if (teacherQuery.getBegin() != null) wrapper.ge("gmt_create", teacherQuery.getBegin());
        if (teacherQuery.getEnd() != null) wrapper.le("gmt_modified", teacherQuery.getEnd());
        eduTeacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    //添加讲师
    @PostMapping(value = "addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? R.ok() : R.error();
    }

    //根据id查询讲师
    @GetMapping(value = "getTeacher/{id}")
    public R getTeacher(@PathVariable("id") String id) {
        return R.ok().data("teacher", eduTeacherService.getById(id));
    }

    //修改讲师
    @PostMapping(value = "updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.updateById(eduTeacher) ? R.ok() : R.error();
    }
}

