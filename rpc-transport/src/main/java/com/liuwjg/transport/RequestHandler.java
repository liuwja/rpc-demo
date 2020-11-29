package com.liuwjg.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求接口
 */
public interface RequestHandler {
    void onRequest(InputStream inputStream, OutputStream outputStream);
}
