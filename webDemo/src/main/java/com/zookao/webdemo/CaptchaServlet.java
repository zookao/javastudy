package com.zookao.webdemo;

/**
 * User: zookao
 * Date: 2021-10-27
 */

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bi = new BufferedImage(210,70,BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bi.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,210,70);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null,Font.BOLD,40));
        graphics.drawString(getNum(),30,50);
        response.setContentType("image/png");
        response.setHeader("Cache-Control","no-cache");
        ImageIO.write(bi,"png", response.getOutputStream());
    }

    private String getNum(){
        StringBuffer sb = new StringBuffer();
        while (sb.length() < 6){
            int v = (int) (Math.random() * 10);
            sb.append(String.valueOf(v));
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
