package com.liuwjg.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 *
 * @author liuwj
 */
public class ReflectionUtils {

    /**
     * 根据class创建对象
     *
     * @param clazz 对象类型
     * @param <T>   对象类型
     * @return 返回对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取class的公共方法
     *
     * @param clazz
     * @return
     */
    public static Method[] getDeclaredMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> returnMethods = new ArrayList();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                returnMethods.add(method);
            }
        }
        return returnMethods.toArray(new Method[0]);
    }

    public static Object invoke(Object object, Method method, Object... agrs) {
        try {
            return method.invoke(object, agrs);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
