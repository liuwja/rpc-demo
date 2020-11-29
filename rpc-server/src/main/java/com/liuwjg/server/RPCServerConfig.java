package com.liuwjg.server;

import com.liuwjg.codec.Decoder;
import com.liuwjg.codec.Encoder;
import com.liuwjg.codec.impl.JSONDecoder;
import com.liuwjg.codec.impl.JSONEncoder;
import com.liuwjg.transport.TransportServer;
import com.liuwjg.transport.impl.HTTPTransportServerImpl;
import lombok.Data;

/**
 * server配置
 */
@Data
public class RPCServerConfig {
    private Class<? extends TransportServer> transportCalss = HTTPTransportServerImpl.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
