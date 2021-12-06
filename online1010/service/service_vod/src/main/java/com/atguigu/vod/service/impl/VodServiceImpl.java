package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadAlyiVideo(MultipartFile file) { //上传视频到阿里云
        String accessKeyId = ConstantVodUtils.ACCESS_KEY_ID; //id
        String accessKeySecret = ConstantVodUtils.ACCESS_KEY_SECRET; // 密钥
        String fileName = file.getOriginalFilename(); //上传文件原始名称
        String title = fileName.substring(0, fileName.lastIndexOf(".")); // 上传之后显示的名称
        String videoId;
        try {
            InputStream inputStream = file.getInputStream(); //上传文件输入流
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return videoId;
    }

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) { //删除多个阿里云视频
        try {
            //1 初始化对象
            DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //2 创建删除视频的request对象
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            //3 向request设置视频id
                //讲videoIdList中的值变为1，2，3的形式
            String join = StringUtils.join(videoIdList, ",");
            deleteVideoRequest.setVideoIds(join);
            //4 调用初始化对象的方法进行删除
            defaultAcsClient.getAcsResponse(deleteVideoRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
