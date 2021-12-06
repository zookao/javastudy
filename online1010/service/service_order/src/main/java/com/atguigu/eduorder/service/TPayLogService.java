package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);

    void updateOrdersStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
