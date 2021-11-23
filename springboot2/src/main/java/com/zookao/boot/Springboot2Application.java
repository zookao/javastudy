package com.zookao.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
// @MapperScan("com.zookao.boot.mapper") //不用再为每个mapper注解@Mapper了
@ServletComponentScan("com.zookao.boot.servlet")
@SpringBootApplication //复合索引
public class Springboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2Application.class, args);
    }

}
