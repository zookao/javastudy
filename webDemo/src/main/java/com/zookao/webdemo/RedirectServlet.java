package com.zookao.webdemo; /**
 * User: zookao
 * Date: 2021-10-27
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/captcha").forward(request,response);
        // response.sendRedirect("/test/captcha");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
