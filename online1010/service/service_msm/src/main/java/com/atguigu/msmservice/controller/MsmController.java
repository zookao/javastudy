package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/edumsm/msm")
@RestController
public class MsmController {
    @Autowired
    MsmService msmService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1 从redis中获取验证码 如果获取到 则直接返回
        String s = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(s)) return R.ok();
        //2 如果redis获取不到 则进行阿里云短信发送
        //生成随机值 传递给阿里云进行发送
        String code = RandomUtil.getFourBitRandom(); //生成4位随机数
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service方法进行短信发送
        boolean flag = msmService.send(param,phone);
        if(flag) {
            //发送成功 把验证码加入redis 并且设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.error();
    }
}
