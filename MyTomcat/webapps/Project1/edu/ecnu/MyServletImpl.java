package ecnu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: zookao
 * Date: 2021-12-17
 */
public class MyServletImpl implements MyServlet {
    public String doPost() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "my tomcat is running at " + df.format(new Date());
    }
}