package com.liuwjg.example;

import com.liuwjg.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService service = rpcClient.getProxy(CalcService.class);
//        int a = service.add(1,2);
        BeanDemo beanDemo = service.getBeanDemo();
        System.out.println(beanDemo);

//        BeanDemo beanDemo = new BeanDemo();
//        beanDemo.setName("刘伟杰");
//        beanDemo.setEmail("liuwjg@fotile.com");
//        BeanDemo beanDemo1 = service.getBeanDemo(beanDemo);

    }
}
