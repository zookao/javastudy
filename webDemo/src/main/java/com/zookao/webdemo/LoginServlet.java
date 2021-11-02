package com.zookao.webdemo; /**
 * User: zookao
 * Date: 2021-10-27
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // ServletOutputStream outputStream = response.getOutputStream();
        // outputStream.write(username.getBytes(StandardCharsets.UTF_8));
        response.sendRedirect("/test/success.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
