package com.liuwjg.transport;

import com.liuwjg.proto.Peer;

import java.io.InputStream;

/**
 * 1、创建链接
 * 2、发送数据并等待响应
 * 3、关闭链接
 * @author liuwj
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
