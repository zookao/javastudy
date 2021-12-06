package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.mapper.EduCommentMapper;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public HashMap<String,Object> getCommentList(long page, long limit, String courseId) {//根据课程id分页查询评论
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");
        Page<EduComment> commentPage = new Page<>(page,limit);
        baseMapper.selectPage(commentPage,wrapper);
        long pages = commentPage.getPages();//总页数
        long size = commentPage.getSize();//每页记录数
        long total = commentPage.getTotal();//总条数
        List<EduComment> list = commentPage.getRecords();//评论集合
        long current = commentPage.getCurrent();//当前页
        boolean hasNext = commentPage.hasNext();//是否有下一页
        boolean hasPrevious = commentPage.hasPrevious();//是否有上一页
        HashMap<String,Object> map = new HashMap<>();
        map.put("pages",pages);
        map.put("size",size);
        map.put("total",total);
        map.put("items",list);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }
}
