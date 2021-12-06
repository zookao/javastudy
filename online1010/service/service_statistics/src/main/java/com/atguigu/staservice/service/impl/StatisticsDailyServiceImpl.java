package com.atguigu.staservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.staservice.client.UcenterClient;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.mapper.StatisticsDailyMapper;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-17
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
        // 删除统计表中同一天的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        //远程调用得到某一天注册的人数
        R r = ucenterClient.countRegister(day);
        int count = (int)r.getData().get("count");
        //把获取的数据加入数据库统计分析表里面
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(count); //注册人数
        statisticsDaily.setDateCalculated(day);//统计日期
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        statisticsDaily.setLoginNum(loginNum);//TODO
        statisticsDaily.setVideoViewNum(videoViewNum);//TODO
        statisticsDaily.setCourseNum(courseNum);//TODO
        baseMapper.insert(statisticsDaily);
    }

    @Override //返回某一时间区间内的统计数量 日期
    public Map<String, Object> getChartData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.orderByAsc("date_calculated");
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        List<String> date = new ArrayList<>();
        List<Integer> cnt  = new ArrayList<>();
        for(StatisticsDaily sta:staList){
            date.add(sta.getDateCalculated());
            switch (type){
                case "login_num":
                    cnt.add(sta.getLoginNum());
                    break;
                case "register_num":
                    cnt.add(sta.getRegisterNum());
                    break;
                case "video_view_num":
                    cnt.add(sta.getVideoViewNum());
                    break;
                case "course_num":
                    cnt.add(sta.getCourseNum());
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dataList",cnt);
        map.put("dateList",date);
        return map;
    }
}
