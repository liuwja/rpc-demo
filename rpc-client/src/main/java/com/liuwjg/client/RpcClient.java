package com.liuwjg.client;

import com.liuwjg.codec.Decoder;
import com.liuwjg.codec.Encoder;
import com.liuwjg.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;
import java.sql.Ref;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    private RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecodeClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        selector.init(config.getServers(),
                config.getConnectCount(),
                config.getTransportClass());

    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz}, new RemoteInvoker(clazz, encoder, decoder, selector));
    }

}
