package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssSerrvice {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
