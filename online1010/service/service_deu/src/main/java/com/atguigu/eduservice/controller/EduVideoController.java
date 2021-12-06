package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-09
 */
/*
@CrossOrigin
*/
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    EduVideoService eduVideoService;

    //注入VodClient
    @Autowired
    VodClient vodClient;
    //添加小节
    @PostMapping("addVideo")
    public R AddVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //删除小节 同时微服务删除视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id
        EduVideo byId = eduVideoService.getById(id);
        String videoSourceId = byId.getVideoSourceId();
        if(videoSourceId!=null){
            //传入视频id 远程调用 删除视频
            R r = vodClient.removeAlyVideo(videoSourceId);
            if(r.getCode()==20001){
                System.out.println("删除视频失败 熔断器");
                return R.error();
            }
        }
        //删除小节
        eduVideoService.removeById(id);
        return R.ok();
    }

    //根据小节id获取小节信息
    @GetMapping("getVideoById/{id}")
    public R getVideoById(@PathVariable String id) {
        EduVideo byId = eduVideoService.getById(id);
        return R.ok().data("video", byId);
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }
}

