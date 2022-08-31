package com.test.spring.test;

import com.test.spring.proxy.MyStaticProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyStaticProxy {
    public static void main(String[] args) {
        //通过spring容器创建student对象
        //1.初始化Spring容器，加载spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        //2.通过spring容器获取Student对象
        MyStaticProxy proxy = (MyStaticProxy) context.getBean("myStaticProxy");
        proxy.delete();

    }
}
