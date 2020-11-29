package com.liuwjg.common.utils.test;

import com.liuwjg.common.utils.ReflectionUtils;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionUtilsTest {
    @Test
    public void newInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        System.out.println(testClass != null);
    }

    @Test
    public void getDeclaredMethods() {
        Method[] methods = ReflectionUtils.getDeclaredMethods(TestClass.class);
        System.out.println(methods.length == 1);
        String name = methods[0].getName();
        System.out.println(name);
    }
    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getDeclaredMethods(TestClass.class);
        Method c = methods[0];
        TestClass testClass = new TestClass();
        Object o = ReflectionUtils.invoke(testClass, c);
        System.out.println(o);
    }
}
