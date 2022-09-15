package com.org.hello;

public class HelloTemplate {
    private  HelloProperties helloProperties;

    public HelloTemplate(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public void add(){
        System.out.println("HelloTemplate----------add" + helloProperties.getPro1());
    }

    public void del(){
        System.out.println("HelloTemplate----------del" + helloProperties.getPro2());
    }

    public void list(){
        System.out.println("HelloTemplate----------list" + helloProperties.getPro3());
    }
}
