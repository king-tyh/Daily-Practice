package com.unit.test.service;

import com.unit.test.bean.Student;
import java.util.List;

public interface StudentService {
    Student getByName(String name);

    List<Student> listStudent();

    void save(Student student);

    void batchSave(Iterable<Student> students);
}
