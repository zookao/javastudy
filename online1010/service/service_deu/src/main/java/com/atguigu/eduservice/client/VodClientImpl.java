package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient{ //熔断 接口出错时执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R removeMoreAlyVideo(List<String> videoIdList) {
        return R.error().message("删除视频出错");
    }
}
