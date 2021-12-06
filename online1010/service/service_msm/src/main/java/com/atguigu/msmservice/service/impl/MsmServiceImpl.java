package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(Map<String, Object> code, String phone) { //发送短信
        if(StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4G71ojnxN6DGB2y8pBJ8", "vUcRG9z0pQjPR7u4f0VS8303GxTcik");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST); //提交方式
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone);//手机号
        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请的阿里云的签名名称
        request.putQueryParameter("TemplateCode","SMS_211487824");//申请的阿里云的模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));//验证码 但是要转变为json形式

        //最终发送
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            return commonResponse.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
