package com.test.springboot.demo.impl;

import com.test.springboot.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

public class MongoControllerImpl {
    @Autowired
    private MongoTemplate mongoTemplate;


    public void insert() {
        User user = new User();
        user.setId(100L);
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setEmail("fisher@qq.com");
        user.setCourses(Arrays.asList("Spring Data", "SpringBoot"));
        mongoTemplate.in
    }

}
