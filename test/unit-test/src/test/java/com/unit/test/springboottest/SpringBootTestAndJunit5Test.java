package com.unit.test.springboottest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
class SpringBootTestAndJunit5Test {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        log.info("setUp...");
        // 初始化mockMvc对象
        // 指定webApplicationContext上下文，将会从这个上下文获取对应的控制器并得到相应的mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        log.info("setUp end...");
    }

    @AfterEach
    void tearDown() {
        log.info("@After");
    }

    @Test
    void test_junitControllerNotParam_404() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/unit/test/get/has/param")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }

    @Test
    void test_junitControllerNotParam() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/unit/test/not/param")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(200, status);
        Assertions.assertEquals("没有请求参数的单元测试get方法", content);
    }
}