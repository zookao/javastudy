package ecnu;

import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zookao
 * Date: 2021-12-17
 */
public class MyServer {

    private final int port;

    private final Map<String, String> servletMap = new HashMap<>();

    public MyServer(int port) {
        this.port = port;
    }

    public void start() {
        initServletMapping();

        try {
            ServerSocket server = new ServerSocket(port);
            Socket accept = server.accept();
            // 读取客户端数据
            BufferedReader input = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            // 向客户端回复信息
            PrintStream out = new PrintStream(accept.getOutputStream());
            while (true) {

                String clientInputStr = input.readLine();
                System.out.println("接收 " + clientInputStr);

                String result = dispatch(clientInputStr);
                System.out.println("返回 " + result);
                out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initServletMapping() {
        XmlUtil xml = new XmlUtil("webapps/Project1/WEB-INF/web.xml");
        //将所有的类都存储到容器中
        List<Element> list = xml.getNodes("Servlet");
        for (Element element : list) {
            servletMap.put(element.element("url").getText(), element.element("class").getText());
        }
        System.out.println("加载web.xml映射结束");
    }

    private String dispatch(String content) {
        String servlet = content.split("/")[1];
        String servletClazz = servletMap.get(servlet);

        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(servletClazz);
            MyServlet myServlet = myServletClass.newInstance();
            return myServlet.doPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // new MyServer(8080).start();
        int i = 1;
        i = i++; // i = 1
        int j = i++; // i = 2 j=1
        int k = i + ++i; // i = 3 j = 1 k = 5
        int x = i + ++i + i++;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("k = " + k);
        System.out.println("x = " + x);
    }
}

