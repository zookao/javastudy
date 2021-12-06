package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //分页查询讲师
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        //把分页的数据封装到pageTeacher中
        baseMapper.selectPage(pageTeacher,wrapper);
        HashMap<String, Object> map = new HashMap<>();
        long current = pageTeacher.getCurrent(); //当前页
        List<EduTeacher> records = pageTeacher.getRecords();//讲师集合
        long total = pageTeacher.getTotal();//总记录数
        long size = pageTeacher.getSize();//每页记录数
        long pages = pageTeacher.getPages();//总页数
        boolean hasNext = pageTeacher.hasNext(); //是否有下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//是否有上一页
        map.put("current",current);
        map.put("records",records);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }
}
