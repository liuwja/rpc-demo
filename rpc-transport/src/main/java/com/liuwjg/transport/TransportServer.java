package com.liuwjg.transport;

/**
 * 1、启动监听
 * 2、接受请求
 * 3、关闭监听
 * @author liuwj
 */
public interface TransportServer {
    /**
     * 初始化方法
     * @param port
     * @param handler
     */
    void init(int port,RequestHandler handler);

    /**
     * 启动
     */
    void start();

    /**
     * 关闭
     */
    void stop();
}
