package com.unit.test.springboottest;

import com.unit.test.bean.Student;
import com.unit.test.controller.StudentController;
import com.unit.test.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 如果要测试 Spring MVC controllers 是否按预期那样工作，则用 @WebMvcTest 注解。@WebMvcTest 注解可自动配置 Spring MVC，
 * 并会限制扫描 @Controller 和 @ControllerAdvice 等注解的 Bean。通常，@WebMvcTest 仅限于单个 Controller，
 * 并结合 @MockBean 注解提供对某个类的模拟实现。@WebMvcTest 还会自动配置MockMvc。MockMvc 提供了一个强大的方法可以快速测试 MVC 控制器，
 * 并且无须启动一个完整的 HTTP 服务器
 */
@WebMvcTest(StudentController.class)
public class MyControllerTest {
    /**
     * 注入MockMvc
     */
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testExample() throws Exception {
        BDDMockito.given(this.studentService.getByName("ZhangSan")).willReturn(new Student("ZhangSan", 15));
        this.mvc.perform(MockMvcRequestBuilders.get("/student/name")
                .param("name", "ZhangSan")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ZhangSan"));
    }
}
