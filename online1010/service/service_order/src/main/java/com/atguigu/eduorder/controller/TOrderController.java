package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@RestController
@RequestMapping("/eduorder/t-order")
public class TOrderController {

    @Autowired
    TOrderService tOrderService;

    //1 生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        // 通过request 获取用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单 返回订单号
        String orderNo = tOrderService.createOrder(courseId,id);
        return R.ok().data("orderId",orderNo);
    }
    //2 根据订单号查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_no",orderId);
        TOrder one = tOrderService.getOne(wrapper);

        return R.ok().data("item",one);
    }
    //根据课程id 用户id 查看是否购买过
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        return tOrderService.count(wrapper)>0;
    }
}

