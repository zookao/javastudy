package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssSerrvice;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssSerrvice {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        InputStream inputStream = null;
        try {
            // 获取上传文件流。
            inputStream = file.getInputStream();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();
            //1 在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            originalFilename = uuid + originalFilename;
            //2 把文件按照日期分类
            // 获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            originalFilename = datePath + "/" + originalFilename;
            ossClient.putObject(bucketName, originalFilename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //手动拼接路径并返回
            String url = "https://" + bucketName + "." + endpoint + "/" + originalFilename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
