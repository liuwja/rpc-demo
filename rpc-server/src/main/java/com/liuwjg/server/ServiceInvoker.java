package com.liuwjg.server;

import com.liuwjg.common.utils.ReflectionUtils;
import com.liuwjg.proto.Request;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance instance, Request request) {
        return ReflectionUtils.invoke(instance.getTarget(), instance.getMethod(), request.getParameters());
    }
}
