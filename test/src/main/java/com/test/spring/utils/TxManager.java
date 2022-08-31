package com.test.spring.utils;

public class TxManager {
    private void begin() {
        System.out.println("-----开启事务");
    }

    private void commit() {
        System.out.println("-----提交事务");
    }
}
