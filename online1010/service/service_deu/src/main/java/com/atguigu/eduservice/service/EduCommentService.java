package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface EduCommentService extends IService<EduComment> {

    HashMap<String,Object> getCommentList(long page, long limit, String courseId);
}
