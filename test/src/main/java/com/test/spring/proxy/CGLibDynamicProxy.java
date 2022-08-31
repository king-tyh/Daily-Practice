package com.test.spring.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 1.添加CGLib依赖
 * 2.创建一个类，实现MethodInterceptor接口，同时实现接口中的intercept方法
 * 3.定义一个Object变量，并提供有参构造器
 * 4.定义getProxy方法创建并返回代理对象（代理对象是通过创建被代理类的子类来创建的）
 */
public class CGLibDynamicProxy implements MethodInterceptor {
    private Object object;
    public CGLibDynamicProxy(Object object){
        this.object = object;
    }

    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        Object returnValue = method.invoke(object);
        commit();
        return returnValue;
    }
    private void begin() {
        System.out.println("-----开启事务");
    }

    private void commit() {
        System.out.println("-----提交事务");
    }

}
