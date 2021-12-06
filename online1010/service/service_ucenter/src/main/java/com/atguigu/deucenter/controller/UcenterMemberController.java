package com.atguigu.deucenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.deucenter.entity.UcenterMember;
import com.atguigu.deucenter.entity.vo.RegisterVo;
import com.atguigu.deucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-14
 */
@RestController
@RequestMapping("/deucenter/ucenter-member")
public class UcenterMemberController {

    @Autowired
    UcenterMemberService service;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        String token = service.login(member);
        System.out.println("登录成功!");
        return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        service.register(registerVo);
        return R.ok();
    }
    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //利用工具类  传入request 获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember byId = service.getById(memberId);
        return R.ok().data("userInfo",byId);
    }
    //根据用户id获取用户信息
    @PostMapping("geeUserInfoOrder/{id}")
    public UcenterMemberOrder geeUserInfoOrder(@PathVariable String id){ //因为在不同项目中 所以我们在common中建了一个类 用于返回用户信息
        UcenterMember ucenterMember = service.getById(id);
        //把ucenterMember复制给UcenterMemberOrder
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
    //查询某一天的注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day") String day){
        int count = service.countRegister(day);
        return R.ok().data("count",count);
    }
}

