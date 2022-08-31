package com.test.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
* 1.JDK动态代理：是通过被代理对象实现的接口产生其代理对象的
* 2.创建一个类，实现InvocationHandler接口，同时实现接口中的Invoke方法
* 3.定义一个Object变量，并提供有参构造器
* 4.定义getProxy方法创建并返回代理对象（代理对象是通过创建被代理类的类加载器和类实现的接口创建的）
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object object;//被代理对象


    public JDKDynamicProxy(Object object) {
        this.object = object;
    }

    //产生代理对象返回代理对象
    public Object getProxy() {
        //1.获取被代理对象的类加载器
        ClassLoader classLoader = object.getClass().getClassLoader();
        //2.获取被代理对象的类实现的接口
        Class<?>[] interfaces = object.getClass().getInterfaces();
        //3.拦截方法执行的处理器InvocationHandler实例，一般实现InvocationHandler接口传入this


        //4.产生代理对象
        //第一个参数：被代理对象的类加载器
        //第二个参数：被代理对象实现的接口
        //第三个参数：使用产生代理对象调用方法时，用于拦截方法执行的处理器
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    private void begin() {
        System.out.println("-----开启事务");
    }

    private void commit() {
        System.out.println("-----提交事务");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        begin();
        Object returnValue = method.invoke(object);//执行被调用的method方法
        commit();
        return returnValue;
    }

}
