package com.zookao.test;

import sun.nio.ch.Net;

/**
 * User: czc
 * Date: 2021/9/25
 * 代理设计模式
 * 例：明星-经纪人
 */
public class ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        ProxyServer ps = new ProxyServer(server);
        ps.browse();
    }
}

interface Network{
    public abstract void browse();
}

//被代理类
class Server implements Network{
    @Override
    public void browse() {
        System.out.println("真实的网络访问");
    }
}

//代理类
class ProxyServer implements Network{
    private Network worker;
    public ProxyServer(Network network){
        this.worker = network;
    }
    public void check(){
        System.out.println("联网之前的检测");
    }
    @Override
    public void browse() {
        check();
        worker.browse();
    }
}