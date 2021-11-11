package com.zookao.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: zookao
 * Date: 2021-11-11
 * 拦截器类
 */
public class MyInterceptor implements HandlerInterceptor {

    /*
    handler是指被拦截的控制器对象
    1、控制器方法之前执行
    2、在这个方法可以获取请求信息，验证请求是否符合要求
       可以验证用户是否登录，验证用户是否有权限访问某个链接
       如果验证失败可以截断请求，请求不被处理
       若果验证成功，可以放行请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器MyInterceptor.preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
        // request.getRequestDispatcher("/index.jsp").forward(request,response);
        // return false;
    }

    /*
    handler是指被拦截的控制器对象
    ModelAndView 处理器方法的返回值
    1、之后执行
    2、可以修改方法的返回值
    3、主要是对返回结果二次修正
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器MyInterceptor.postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        // if(modelAndView != null){
        //     modelAndView.addObject();
        //     modelAndView.setViewName();
        // }
    }

    /*
    最后执行的方法
    handler是指被拦截的控制器对象
    Exception 异常
    1、请求处理完成后执行。当视图处理完成后或对视图完成了forward
    2、一般做资源的回收工作，比如创建的对象的删除。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器MyInterceptor.afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
