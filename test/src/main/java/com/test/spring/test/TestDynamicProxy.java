package com.test.spring.test;

import com.test.spring.DAO.BaseDao;
import com.test.spring.DAO.BookDaoImpl;
import com.test.spring.proxy.CGLibDynamicProxy;
import com.test.spring.proxy.JDKDynamicProxy;

public class TestDynamicProxy {
    public static void main(String[] args) {
        //被代理对象
        BookDaoImpl bookDao = new BookDaoImpl();
        //proxy就是产生的代理对象：可以强转成被代理对象实现的接口类型
        BaseDao proxy = (BaseDao) new JDKDynamicProxy(bookDao).getProxy();
        //使用代理对象调用方法，不会直接进入到代理类(并不会执行调用的方法)，而是进入到创建代理对象时指定的拦截器中invoke方法
        //调用的方法和参数作为一个参数传送给invoke方法
        proxy.delete();

        CGLibDynamicProxy cgLibDynamicProxy = new CGLibDynamicProxy(bookDao);
        BookDaoImpl cgProxy = (BookDaoImpl) cgLibDynamicProxy.getProxy();
        //使用代理对象调用方法，不会直接进入到代理类(并不会执行调用的方法)，而是执行了代理类中的intercept方法
        //调用的方法和参数作为一个参数传送给intercept方法
        cgProxy.delete();
    }
}
