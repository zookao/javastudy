package com.zookao.boot.config;

import com.zookao.boot.interceptor.LoginInterceptor;
import com.zookao.boot.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * User: zookao
 * Date: 2021-11-16
 */
// @EnableWebMvc //全面接管mvc，会导致静态资源访问不到
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","/css/**","/js/**","/fonts/**","/images/**");

        registry.addInterceptor(redisUrlCountInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login","/css/**","/js/**","/fonts/**","/images/**");;
    }
}
