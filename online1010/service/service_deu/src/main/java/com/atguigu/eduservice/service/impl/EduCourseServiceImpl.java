package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.frontVo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontVo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-09
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表加数据
        //将参数转为eduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);//将courseInfoVo的数据复制给eduCourse
        boolean save = this.save(eduCourse);

        if (!save) {
            System.out.println("添加失败");
            return null;
        }
        String pid = eduCourse.getId();
        //2 向课程简介表加数据
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        eduCourseDescription.setId(pid);
        boolean save1 = eduCourseDescriptionService.save(eduCourseDescription);

        return pid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {//根据id返回课程信息
        EduCourse byId = this.getById(courseId);
        EduCourseDescription byId1 = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(byId, courseInfoVo);
        courseInfoVo.setDescription(byId1.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {//修改课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean b = this.updateById(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        boolean b1 = eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public Page getCourseQuery(int page, int limit, CourseQuery courseQuery) { //分页+模糊查询课程
        Page<EduCourse> page1 = new Page<>(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (courseQuery.getTitle() != null) queryWrapper.like("title", courseQuery.getTitle());
        if (courseQuery.getStatus() != null) queryWrapper.eq("status", courseQuery.getStatus());
        if (courseQuery.getGmtCreate() != null) queryWrapper.ge("gmt_create", courseQuery.getGmtCreate());
        if (courseQuery.getGmtModified() != null) queryWrapper.le("gmt_modified", courseQuery.getGmtModified());
        this.page(page1, queryWrapper);
        return page1;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> page1, CourseFrontVo courseFrontVo) {//前台分页+条件查询
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件是否为空,不为空则拼接条件
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){ //判断一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())){//判断二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){ //关注度(销量)
            wrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){//时间
            wrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getPriceSort())){//价格
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(page1, wrapper);  //分页+条件查询

        List<EduCourse> records = page1.getRecords(); //课程集合
        long current = page1.getCurrent(); //当前页
        long pages = page1.getPages();// 总页数
        long size = page1.getSize(); // 每页记录数
        long total = page1.getTotal(); //总记录数
        boolean hasNext = page1.hasNext(); //是否有下一页
        boolean hasPrevious = page1.hasPrevious(); //是否有上一页

        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map; //返回
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        CourseWebVo courseWebVo = baseMapper.getBaseCourseInfo(courseId);
        return courseWebVo;
    }
}
