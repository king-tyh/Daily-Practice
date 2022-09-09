package com.unit.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }
}
