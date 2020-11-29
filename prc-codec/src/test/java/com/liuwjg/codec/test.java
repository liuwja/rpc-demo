package com.liuwjg.codec;

import com.liuwjg.codec.impl.JSONDecoder;
import com.liuwjg.codec.impl.JSONEncoder;
import org.junit.Test;

public class test {
    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setAge(19);
        testBean.setName("liuwjg");

        byte[] bytes = encoder.encode(testBean);
        System.out.println(bytes);
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean testBean = new TestBean();
        testBean.setAge(19);
        testBean.setName("liuwjg");
        byte[] bytes = encoder.encode(testBean);

        Decoder decoder = new JSONDecoder();
        TestBean testBean1 = decoder.decode(bytes, TestBean.class);
        System.out.println(testBean1.toString());
    }
}
