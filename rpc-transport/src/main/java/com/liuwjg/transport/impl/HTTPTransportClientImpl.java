package com.liuwjg.transport.impl;

import com.liuwjg.proto.Peer;
import com.liuwjg.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http客户端实现类
 */
public class HTTPTransportClientImpl implements TransportClient {

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();

            IOUtils.copy(data, httpURLConnection.getOutputStream());
            int resultCode = httpURLConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
