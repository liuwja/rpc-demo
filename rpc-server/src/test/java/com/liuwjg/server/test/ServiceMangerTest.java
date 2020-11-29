package com.liuwjg.server.test;

import com.liuwjg.common.utils.ReflectionUtils;
import com.liuwjg.proto.Request;
import com.liuwjg.proto.ServiceDescriptor;
import com.liuwjg.server.ServiceInstance;
import com.liuwjg.server.ServiceManager;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class ServiceMangerTest {

    ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }
    @Test
    public void lookup(){
        Method method = ReflectionUtils.getDeclaredMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class,method);
        Request request = new Request();
        request.setService(serviceDescriptor);
        ServiceInstance instance =  serviceManager.lookup(request);
        System.out.println(instance);


    }
}
