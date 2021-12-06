package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            int i = 10 / 0;
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject eduOneSubject = this.oneExcel(eduSubjectService, subjectData.getOneSubjectName());
        if (eduOneSubject == null) {
            eduOneSubject = new EduSubject();
            eduOneSubject.setParentId("0");
            eduOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduOneSubject);
        }
        String pid = eduOneSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.twoExcel(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            eduSubjectService.save(existTwoSubject);
        }
    }

    //判断一级分类是否重复
    public EduSubject oneExcel(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        return eduSubjectService.getOne(wrapper);
    }

    //判断二级分类是否重复
    public EduSubject twoExcel(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return eduSubjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
