package com.org.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(HelloTemplate.class)
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnProperty(prefix = "spring.hello",name = {"pro1","pro2","pro3"},matchIfMissing = false)
public class HelloAutoConfig {
    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloTemplate getHelloTemplate(){
        return new HelloTemplate(helloProperties);
    }
}
