package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.mapper.TOrderMapper;
import com.atguigu.eduorder.service.TOrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    EduClient eduClient;
    @Autowired
    UcenterClient ucenterClient;
    @Override
    public String createOrder(String courseId, String id) {
        System.out.println("课程Id: "+courseId);
        //通过远程调用 根据用户id获取用户信息
        UcenterMemberOrder ucenterMemberOrder = ucenterClient.geeUserInfoOrder(id);
        System.out.println("用户信息:"+ucenterMemberOrder);
        //通过远程调用 根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        System.out.println("课程信息:"+courseInfoOrder);
        //创建Order对象 向Order对象设置数据
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号 (工具类生成)
        order.setCourseId(courseId);//课程id
        order.setCourseTitle(courseInfoOrder.getTitle()); //课程标题
        order.setCourseCover(courseInfoOrder.getCover()); //课程封面
        order.setTeacherName(courseInfoOrder.getTeacherName());//讲师姓名
        order.setTotalFee(courseInfoOrder.getPrice());//课程价格
        order.setMemberId(ucenterMemberOrder.getId()); //用户id
        order.setMobile(ucenterMemberOrder.getMobile());//用户手机
        order.setNickname(ucenterMemberOrder.getNickname());//用户昵称

        order.setStatus(0);//订单支付状态
        order.setPayType(1);// 支付类型 1代表微信 2代表支付宝
        baseMapper.insert(order);

        //返回订单号
        return order.getOrderNo();
    }
}
