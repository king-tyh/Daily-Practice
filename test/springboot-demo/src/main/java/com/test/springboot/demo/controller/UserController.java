package com.test.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/test")
    public String test() {
        System.out.println("----------UserController test");
        return "index";
    }

    @RequestMapping("/dev")
    public String dev() {
        System.out.println("----------UserController dev");
        return "index";
    }
}
