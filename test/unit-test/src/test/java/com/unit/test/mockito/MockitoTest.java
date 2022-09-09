package com.unit.test.mockito;

import com.unit.test.bean.Student;
import com.unit.test.bean.Student.Subject;
import com.unit.test.bean.Student.SubjectScore;
import com.unit.test.controller.JunitController;
import com.unit.test.service.StudentService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MockitoTest {
    private JunitController junitController;
    private StudentService studentService;

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ5YW5ncGluZyJ9.-oPrnr36OSLVHH0SyInlh4Um55-Pgs_KUL9K-IcvS1E";

    private MockHttpServletRequest request;

    @Before
    public void setUp() {
        junitController = new JunitController();
        studentService = Mockito.mock(StudentService.class);
        Whitebox.setInternalState(junitController, "studentService", studentService);

        // Mock HttpServletRequest
        request = new MockHttpServletRequest();
    }

    @Test
    public void test_getStudents() {
        Mockito.when(studentService.listStudent()).thenReturn(mockData());
        List<Student> studentList = junitController.getStudents();
        Assert.assertEquals(1, studentList.size());
    }

    @Test
    public void test_add() {
        List<Student> students = mockData();
        String result = junitController.add(students);
        Assert.assertEquals("success", result);

        // Mockito提供vertify关键字来实现校验方法是否被调用， 通过verify可以交易业务执行流程
        Mockito.verify(studentService, Mockito.times(1)).save(Mockito.any(Student.class));
    }


    @Test
    public void test_checkAccount_fail() {
        request.addHeader("token", "");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String result = junitController.checkAccount();
        Assert.assertEquals("失败", result);
    }

    @Test
    public void test_checkAccount_success() {
        request.addHeader("token", token);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String result = junitController.checkAccount();
        Assert.assertEquals("成功", result);
    }

    public List<Student> mockData() {
        List<Student> studentList = new ArrayList<>();
        Student zhangsan = new Student();
        zhangsan.setName("zhangsan");

        SubjectScore chinese = new SubjectScore();
        chinese.setSubject(Subject.CHINESE);
        chinese.setScore(100.0);

        SubjectScore math = new SubjectScore();
        math.setSubject(Subject.MATH);
        math.setScore(59.0);

        SubjectScore english = new SubjectScore();
        english.setSubject(Subject.ENGLISH);
        english.setScore(78.0);
        zhangsan.setSubjectScores(Arrays.asList(chinese, math, english));
        studentList.add(zhangsan);
        return studentList;
    }
}