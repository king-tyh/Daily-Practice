package com.unit.test.controller;

import com.unit.test.bean.Student;
import com.unit.test.service.StudentService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unit/test")
public class JunitController extends BaseController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/not/param")
    public String junitControllerNotParam() {
        return "没有请求参数的单元测试get方法";
    }

    @GetMapping("/check/account")
    public String checkAccount() {
        String account = getAccount();
        if (StringUtils.isNotBlank(account)) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam(value = "name") String name) {
        return studentService.getByName(name);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.listStudent();
    }

    @PostMapping("/student")
    public String add(@RequestBody List<Student> students) {
        students.forEach(student -> studentService.save(student));
        return "success";
    }
}
