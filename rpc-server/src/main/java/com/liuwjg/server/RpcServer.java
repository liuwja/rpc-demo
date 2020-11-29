package com.liuwjg.server;

import com.liuwjg.codec.Decoder;
import com.liuwjg.codec.Encoder;
import com.liuwjg.common.utils.ReflectionUtils;
import com.liuwjg.proto.Request;
import com.liuwjg.proto.Response;
import com.liuwjg.transport.RequestHandler;
import com.liuwjg.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RPCServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker invoker;


    public RpcServer() {
        this(new RPCServerConfig());
    }

    public RpcServer(RPCServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportCalss());
        net.init(config.getPort(), this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.invoker = new ServiceInvoker();

    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }


    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream inputStream, OutputStream outputStream) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(inputStream, inputStream.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request {}", request);

                ServiceInstance instance = serviceManager.lookup(request);
                Object o = invoker.invoke(instance, request);
                response.setData(o);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("RpcServer got error" + e.getClass().getName() + e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    outputStream.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    };

}
