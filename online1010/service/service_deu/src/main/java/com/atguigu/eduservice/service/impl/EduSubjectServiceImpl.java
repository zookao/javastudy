package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService) {
        //添加课程分类
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllTree() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = this.list(wrapper);
        List<OneSubject> res = new ArrayList<>();
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject temp = new OneSubject();
            temp.setId(eduSubject.getId());
            temp.setTitle(eduSubject.getTitle());
            QueryWrapper wa = new QueryWrapper();
            wa.eq("parent_id", temp.getId());
            List<EduSubject> list = this.list(wa);
            List<TwoSubject> two = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                TwoSubject u = new TwoSubject();
                u.setId(list.get(j).getId());
                u.setTitle(list.get(j).getTitle());
                two.add(u);
            }
            temp.setChildren(two);
            res.add(temp);
        }
        return res;
    }
}
