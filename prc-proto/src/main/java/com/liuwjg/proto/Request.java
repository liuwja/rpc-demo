package com.liuwjg.proto;

import lombok.Data;

/**
 * 表示请求
 *
 * @author liuwj
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
