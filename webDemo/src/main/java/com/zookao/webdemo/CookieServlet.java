package com.zookao.webdemo;

/**
 * User: zookao
 * Date: 2021-10-29
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ServletOutputStream out = response.getOutputStream();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("lastTime")){
                    Instant instant = Instant.ofEpochMilli(Long.valueOf(cookie.getValue()));
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC+8"));
                    out.write(("您上次访问时间是："+localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))).getBytes(StandardCharsets.UTF_8));
                }
            }
        }else{
            out.write("第一次访问".getBytes(StandardCharsets.UTF_8));
        }
        Cookie c = new Cookie("lastTime", String.valueOf(System.currentTimeMillis()));
        c.setMaxAge(24*60*60); //一天。删除cookie: setMaxAge(0)
        response.addCookie(c);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
