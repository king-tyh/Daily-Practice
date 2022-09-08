package com.test.springboot.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private AudioConverse audioConverse;
    @RequestMapping("/test")
    public byte[] test() {
        System.out.println("----------UserController test");
        return audioConverse.getMp3FromPcm("src/main/resources/20220905165437544672C8631420006B.pcm");
    }

    @RequestMapping("/dev")
    public String dev() {
        System.out.println("----------UserController dev");
        return "index";
    }
}
