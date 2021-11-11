package com.zookao.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: zookao
 * Date: 2021-11-11
 */
public class LogFilter implements Filter {
    //可以访问filter在web.xml中的配置信息
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter初始化成功");
        this.filterConfig = filterConfig;
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext context = this.filterConfig.getServletContext();
        long before = System.currentTimeMillis();
        HttpServletRequest hrequest = (HttpServletRequest) request;
        context.log("LogFilter已经获取到servlet地址"+hrequest.getServletPath());
        chain.doFilter(request,response);
        long after = System.currentTimeMillis();
        context.log("LogFilter已经获取到完整请求地址"+hrequest.getRequestURI()+",花费时间"+(after - before));
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
        Filter.super.destroy();
    }
}
