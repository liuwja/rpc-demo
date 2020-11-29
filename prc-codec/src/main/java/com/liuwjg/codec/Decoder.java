package com.liuwjg.codec;

/**
 * 反序列化
 *
 * @author liuwj
 */
public interface Decoder {
    /**
     * 反序列化方法
     *
     * @param bytes
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
