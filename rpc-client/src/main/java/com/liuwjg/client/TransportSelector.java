package com.liuwjg.client;

import com.liuwjg.proto.Peer;
import com.liuwjg.transport.TransportClient;

import java.util.List;

/**
 * 选择哪一个server去连接
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers  可以连接的server端点信息
     * @param count  建立的连接数
     * @param clazz  client的实现class
     */

    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个Transport与Server做交互
     *
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的client
     *
     * @param client
     */
    void release(TransportClient client);

    void close();
}
