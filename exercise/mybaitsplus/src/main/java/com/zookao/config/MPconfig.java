package com.zookao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: zookao
 * Date: 2021-10-22
 */
@MapperScan("com.zookao.mapper")
@EnableTransactionManagement
@Configuration
public class MPconfig {

}
