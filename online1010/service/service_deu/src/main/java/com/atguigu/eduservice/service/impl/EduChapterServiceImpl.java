package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-09
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoById(String courseId) { //根据课程id获取所有的章节小节
        //1 根据课程id查询课程里面的所有章节
        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<EduChapter> list = this.list(queryWrapper);
        QueryWrapper<EduVideo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        List<EduVideo> list1 = eduVideoService.list(queryWrapper1);
        List<ChapterVo> res = new LinkedList<>();
        //3 遍历章节的所有list 进行封装
        //4 遍历所有小节的list 进行封装
        for (int i = 0; i < list.size(); i++) {
            EduChapter eduChapter = list.get(i);
            ChapterVo u = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, u);
            List<VideVo> temp = new ArrayList<>();
            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(j).getChapterId().equals(u.getId())) {
                    VideVo v = new VideVo();
                    BeanUtils.copyProperties(list1.get(j), v);
                    temp.add(v);
                }
            }
            u.setChildren(temp);
            res.add(u);
        }
        return res;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(queryWrapper);
        if (count == 0) this.removeById(chapterId);
        return count > 0 ? false : true;
    }

    @Override
    public void removeByCourseId(String id) {//根据课程id删除章节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        baseMapper.delete(queryWrapper);
    }
}
