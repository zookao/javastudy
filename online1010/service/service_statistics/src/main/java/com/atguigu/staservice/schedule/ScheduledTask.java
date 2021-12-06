package com.atguigu.staservice.schedule;

import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    StatisticsDailyService service;
    //在每天凌晨1点执行 获取前一天的数据
    @Scheduled(cron = "0 0 1 * * ? ")
    public void test(){
        service.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
