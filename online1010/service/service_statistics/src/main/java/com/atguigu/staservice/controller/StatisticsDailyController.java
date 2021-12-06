package com.atguigu.staservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-17
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService service;

    //统计某一天注册人数
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day){
        service.registerCount(day);
        return R.ok();
    }

    //返回某一时间区间内统计数量 和日期
    @GetMapping("show/{type}/{begin}/{end}")
    public R showChart(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object> map = service.getChartData(type,begin,end);
        return R.ok().data(map);
    }
}

