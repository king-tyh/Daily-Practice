package com.org.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.hello")
public class HelloProperties {

    private String pro1;    //spring.hello.pro1
    private String pro2;    //spring.hello.pro2
    private String pro3;    //spring.hello.pro3


    public String getPro1() {
        return pro1;
    }

    public void setPro1(String pro1) {
        this.pro1 = pro1;
    }

    public String getPro2() {
        return pro2;
    }

    public void setPro2(String pro2) {
        this.pro2 = pro2;
    }

    public String getPro3() {
        return pro3;
    }

    public void setPro3(String pro3) {
        this.pro3 = pro3;
    }

}
