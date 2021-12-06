package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/eduvod/video")
@RestController
public class VodController {
    @Autowired
    VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){
        String videoId = vodService.uploadAlyiVideo(file);
        return R.ok().data("videoId",videoId);
    }
    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try {
            //1 初始化对象
            DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //2 创建删除视频的request对象
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            //3 向request设置视频id
            deleteVideoRequest.setVideoIds(id);
            //4 调用初始化对象的方法进行删除
            defaultAcsClient.getAcsResponse(deleteVideoRequest);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error();
    }
    //删除多个阿里云视频
    @DeleteMapping("delete-bath")
    public R deleteBath(@RequestParam("videoIdList") List videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try{
            //创建初始化对象
            DefaultAcsClient client =
                    InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证的request对象和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

            //向request设置视频Id
            request.setVideoId(id);

            //调用方法 得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();

            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error();
    }
}
