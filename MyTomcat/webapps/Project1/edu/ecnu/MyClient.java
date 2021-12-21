package ecnu;

import java.io.*;
import java.net.Socket;

/**
 * User: zookao
 * Date: 2021-12-17
 */
public class MyClient {

    private final String address;

    private final int port;

    public MyClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start() {
        Socket socket = null;
        try {
            socket = new Socket(address, port);
            //向服务器端发送数据
            PrintStream out = new PrintStream(socket.getOutputStream());
            //读取服务器端数据
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                System.out.println("请求 Project1/MyServlet");
                out.println("Project1/MyServlet");

                String ret = input.readLine();
                System.out.println("响应 " + ret);

                Thread.sleep(3000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyClient("127.0.0.1", 8080).start();
    }
}
