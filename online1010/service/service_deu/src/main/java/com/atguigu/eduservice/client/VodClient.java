package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodClientImpl.class) //调用的服务的名称 熔断
@Component
public interface VodClient { //服务调用

    //定义要调用方法的路径
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);
    @DeleteMapping("/eduvod/video/delete-bath")
    public R removeMoreAlyVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
