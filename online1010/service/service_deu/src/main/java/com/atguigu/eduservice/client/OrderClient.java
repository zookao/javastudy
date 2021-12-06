package com.atguigu.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-order",fallback =OrderClientImpl.class)
public interface OrderClient {

    //根据课程id 用户id 查看是否购买过
    @GetMapping("/eduorder/t-order/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
