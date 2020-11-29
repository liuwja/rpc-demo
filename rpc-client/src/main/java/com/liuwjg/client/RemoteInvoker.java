package com.liuwjg.client;

import com.liuwjg.codec.Decoder;
import com.liuwjg.codec.Encoder;
import com.liuwjg.proto.Request;
import com.liuwjg.proto.Response;
import com.liuwjg.proto.ServiceDescriptor;
import com.liuwjg.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * 远程调用代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream inputStream = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(inputStream, inputStream.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error: " + e.getClass().getName()
                    + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}
