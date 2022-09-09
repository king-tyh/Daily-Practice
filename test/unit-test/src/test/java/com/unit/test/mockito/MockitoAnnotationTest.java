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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {
    // 创建一个实例，简单的说是这个Mock可以调用真实代码的方法，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
    @InjectMocks
    private JunitController junitController;

    // 对函数的调用均执行mock（即虚假函数），不执行真正部分。
    @Mock
    private StudentService studentService;

    // 对函数的调用均执行真正部分。
//    @Spy
//    private XXX xxx;

    @Before
    public void setUp() {
    }

    @Test
    public void test_add() {
        List<Student> students = mockData();
        String result = junitController.add(students);
        Assert.assertEquals("success", result);

        // Mockito提供vertify关键字来实现校验方法是否被调用， 通过verify可以交易业务执行流程
        Mockito.verify(studentService, Mockito.times(2)).save(Mockito.any(Student.class));
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
        studentList.add(zhangsan);
        return studentList;
    }
}
