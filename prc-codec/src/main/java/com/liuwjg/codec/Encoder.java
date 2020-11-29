package com.liuwjg.codec;

/**
 * 序列化接口
 */
public interface Encoder {
    /**
     * 序列化方法
     * @param object
     * @return
     */
    byte[] encode(Object object);
}
