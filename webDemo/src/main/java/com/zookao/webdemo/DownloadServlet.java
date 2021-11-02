package com.zookao.webdemo;

/**
 * User: zookao
 * Date: 2021-10-27
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File("D:\\Desktop\\3.png");

        response.setHeader("Content-Disposition", "attachment;filename="+Math.random()+".png");

        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1){
            out.write(bytes,0,len);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
