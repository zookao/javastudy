package com.atguigu.deucenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.deucenter.entity.UcenterMember;
import com.atguigu.deucenter.service.UcenterMemberService;
import com.atguigu.deucenter.utils.ConstantWxUtils;
import com.atguigu.deucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller //注意这里没有配置 @RestController 因为我们只是请求地址 不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    UcenterMemberService service;

    // 1 生成微信二维码
    @GetMapping("login")
    public String getVxCode(){
        // 微信开放平台授权baseUrl  %s相当与占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" + "&redirect_uri=%s" + "&response_type=code" +
        "&scope=snsapi_login" + "&state=%s" + "#wechat_redirect";
        //对redirect_uri进行编码
        String redirect_uri = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            String encode = URLEncoder.encode(redirect_uri, "utf-8");
            baseUrl = String.format(
                    baseUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    encode,"atguigu"
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //重定向到请求微信地址
        return "redirect:"+baseUrl;
    }

    //2 获取扫描人信息 添加数据
    @GetMapping("callback")
    public String callback(String code,String state) throws Exception { //两个参数 code类似于手机验证码 唯一随机
        //拿着code请求微信固定地址 得到access_token和openid
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=%s&" +
                "secret=%s&" +
                "code=%s&grant_type=authorization_code";
        //拼接三个参数 id 密钥 code
        String format = String.format(baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code
        );
        //请求拼接好的地址 得到返回的两个值 access_code和openid
        //使用httpclient发送请求 得到返回结果
        String s = HttpClientUtils.get(format);
        //从返回的字符串中获取出来的两个值access_token和openid
        //把字符串转为map集合 很具map里面的key获取对应值
        //使用json转换工具Gson
        Gson gson = new Gson();
        HashMap hashMap = gson.fromJson(s, HashMap.class);
        String access_token = (String)hashMap.get("access_token");
        String openid = (String)hashMap.get("openid");
        UcenterMember ucenterMember = service.getOpenIdMember(openid);
        if(ucenterMember==null){ //未使用微信登陆过
            //拿着access_token和openid再去请求微信固定的地址 获取扫码人的信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String format1 = String.format(
                    baseUserInfoUrl,
                    access_token,
                    openid
            );
            String userInfo = HttpClientUtils.get(format1);
            //返回扫描人信息
            HashMap hashMap1 = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String)hashMap1.get("nickname"); //昵称
            String headimgurl = (String)hashMap1.get("headimgurl"); //头像
            //未注册过 添加
            ucenterMember = new UcenterMember();
            ucenterMember.setNickname(nickname);
            ucenterMember.setAvatar(headimgurl);
            ucenterMember.setOpenid(openid);
            service.save(ucenterMember);
            ucenterMember = service.getOpenIdMember(openid);
        }

        //使用jwt根据用户id 昵称生成token字符串
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return "redirect:http://localhost:3000?token="+jwtToken;
    }
}
