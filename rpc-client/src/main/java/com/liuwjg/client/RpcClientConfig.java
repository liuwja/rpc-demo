package com.liuwjg.client;

import com.liuwjg.codec.Decoder;
import com.liuwjg.codec.Encoder;
import com.liuwjg.codec.impl.JSONDecoder;
import com.liuwjg.codec.impl.JSONEncoder;
import com.liuwjg.proto.Peer;
import com.liuwjg.transport.TransportClient;
import com.liuwjg.transport.impl.HTTPTransportClientImpl;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * rpc客户端的配置类
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClientImpl.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decodeClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
