package com.liuwjg.codec.impl;

import com.alibaba.fastjson.JSON;
import com.liuwjg.codec.Decoder;

/**
 * 基于json的反序列化实现
 */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        T object= JSON.parseObject(bytes, clazz);
        return object;
    }
}
