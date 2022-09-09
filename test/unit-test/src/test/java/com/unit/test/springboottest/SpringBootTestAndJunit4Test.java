package com.unit.test.springboottest;

import com.unit.test.bean.Student;
import com.unit.test.controller.JunitController;
import com.unit.test.service.StudentService;
import com.unit.test.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// SpringRunner是SpringJUnit4ClassRunner 的子类，负责在Junit run之前为Test准备Springboot的support，创建context，
// 负责在跑JUnit test之前把Springboot 启动起来。
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class SpringBootTestAndJunit4Test {
    private JunitController junitController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StudentService studentService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        log.info("setUp...");
        // 初始化mockMvc对象
        // 指定webApplicationContext上下文，将会从这个上下文获取对应的控制器并得到相应的mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        log.info("setUp end...");
    }

    @After
    public void tearDown() {
        log.info("@After");
    }

    @Test
    public void test_junitControllerNotParam_404() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/unit/test/get/has/param")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
//            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(404, status);
        log.info("test_junitControllerNotParam_404");
    }

    @Test
    public void test_junitControllerNotParam() throws Exception {
        // MockMvcRequestBuilders.get("/url"):  构造一个get请求
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/unit/test/not/param")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
//            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(200, status);
        Assert.assertEquals("没有请求参数的单元测试get方法", content);
        log.info("test_junitControllerNotParam");
    }

    @Test
    public void test_getStudent_success() throws Exception {
        Student student = new Student();
        student.setName("zhangsan");
        student.setAge(13);
        Mockito.when(studentService.getByName("zhangsan")).thenReturn(student);
        // MockMvcRequestBuilders.get("/url"):  构造一个get请求
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/unit/test/student")
                // 传参
                .param("name", "zhangsan")
                // 请求类型，json
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student convert = JsonUtils.str2obj(Student.class, content);
        Assert.assertEquals(13, convert.getAge().intValue());
        Assert.assertEquals("zhangsan", convert.getName());
        log.info("test_getStudent_success");
    }
}