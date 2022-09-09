package com.unit.test.service;

import com.unit.test.bean.Student;
import com.unit.test.mapper.StudentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired(required = false)
    private StudentMapper mapper;

    @Override
    public Student getByName(String name) {
        return mapper.getByName(name);
    }

    @Override
    public List<Student> listStudent() {
        return mapper.listStudent();
    }

    @Override
    public void save(Student student) {
        mapper.save(student);
    }

    @Override
    public void batchSave(Iterable<Student> students) {
        students.forEach(student -> mapper.save(student));
    }
}
