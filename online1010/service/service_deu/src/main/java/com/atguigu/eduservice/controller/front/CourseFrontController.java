package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.eduservice.client.OrderClient;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontVo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontVo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/eduservice/CourseFront")
public class CourseFrontController {
    @Autowired
    EduCourseService courseService;
    @Autowired
    EduChapterService chapterService;
    @Autowired
    OrderClient orderClient;
    //1 条件查询+分页课程
    @PostMapping("getFrontCourse/{page}/{limit}")
    public R getFrontCourse(@PathVariable long page,@PathVariable long limit,
                            @RequestBody(required = false) CourseFrontVo courseFrontVo){ //Vo类非必须
        Page<EduCourse> page1 = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(page1,courseFrontVo);
        return R.ok().data(map);
    }

    //2 查询课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){

        // 根据课程id 编写Sql语句 查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        // 根据课程id查询章节小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoById(courseId);
        // 根据课程id 用户id 查看是否购买过
        Boolean buyCourse = orderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }
    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("courseId") String courseId){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
