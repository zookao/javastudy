package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

@Data
public class ChapterVo { //章节

    private String id;
    private String title;

    private List<VideVo> children;
}
