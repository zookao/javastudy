package com.atguigu.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.entity.TPayLog;
import com.atguigu.eduorder.mapper.TPayLogMapper;
import com.atguigu.eduorder.service.TOrderService;
import com.atguigu.eduorder.service.TPayLogService;
import com.atguigu.eduorder.utils.HttpClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
    @Autowired
    TOrderService orderService;
    @Override
    public Map createNative(String orderNo) {//生成微信二维码
        try {
            //根据订单id获取订单信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);

            Map m = new HashMap();
            //1、设置支付参数
            m.put("appid", "wx74862e0dfcf69954"); //公众号appid
            m.put("mch_id", "1558950191"); //商户号
            m.put("nonce_str", WXPayUtil.generateNonceStr()); //工具类生成随机的字符串 使二维码不同
            m.put("body", order.getCourseTitle());//课程名称
            m.put("out_trade_no", orderNo);//订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");//订单价格
            m.put("spbill_create_ip", "127.0.0.1");//支付的ip
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");//回调地址
            m.put("trade_type", "NATIVE");//支付类型

            //2、HTTPClient来根据URL访问第三方接口并且传递参数 (utils中的工具类)
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb")); //商户key
            client.setHttps(true);
            client.post();//执行请求
            //3、返回第三方的数据
            String xml = client.getContent();//得到返回的内容 （xml格式）
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml); //将xml格式转为map
            //4、封装返回结果集

            Map map = new HashMap<>(); //最终返回数据
            map.put("out_trade_no", orderNo); //订单号
            map.put("course_id", order.getCourseId()); //课程id
            map.put("total_fee", order.getTotalFee()); //价格
            map.put("result_code", resultMap.get("result_code"));//二维码操作的状态码
            map.put("code_url", resultMap.get("code_url"));//二维码地址

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) { //向支付表加记录 更新订单状态
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);

        if(order.getStatus().intValue() == 1) return;
        order.setStatus(1);
        orderService.updateById(order);

        //记录支付日志 向支付记录表加记录
        TPayLog payLog=new TPayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));//订单流水号
        payLog.setAttr(JSONObject.toJSONString(map));//其他属性 我们用JSON格式将他存到数据库
        baseMapper.insert(payLog);//插入到支付日志表
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {//查询订单支付状态
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            //6、转成Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
