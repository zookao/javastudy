package com.atguigu.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {

    @Autowired
    EduCommentService commentService;
    @GetMapping("getCommentList/{page}/{limit}/{courseId}")
    public R getCommentList(@PathVariable long page,@PathVariable long limit,@PathVariable String courseId){ //分页+条件查询评论
        //根据课程id进行分页查询
        HashMap<String,Object> map =  commentService.getCommentList(page,limit,courseId);
        return R.ok().data(map);
    }

    @PostMapping("/addComment")
    public R addComment(@RequestBody EduComment eduComment){ //增加评论
        commentService.save(eduComment);
        return R.ok();
    }
}

