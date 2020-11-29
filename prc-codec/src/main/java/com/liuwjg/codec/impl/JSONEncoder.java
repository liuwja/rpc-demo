package com.liuwjg.codec.impl;

import com.alibaba.fastjson.JSON;
import com.liuwjg.codec.Encoder;

/**
 * 基于json的序列化实现
 *
 * @author liuwj
 */
public class JSONEncoder implements Encoder {
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}
