package com.liuwjg.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public BeanDemo getBeanDemo() {
        return new BeanDemo();
    }
}
