package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@EnableFeignClients//服务调用
@EnableDiscoveryClient//nacos服务注册
@SpringBootApplication //启动类
@ComponentScan(basePackages = {"com.atguigu"}) //定义组件扫描 扫描com.atguigu下的组件
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
