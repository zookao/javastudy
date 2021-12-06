package com.atguigu.oss.controller;


import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssSerrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    OssSerrvice ossSerrvice;

    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传oss的路径
        System.out.println("--------");
        String url = ossSerrvice.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
