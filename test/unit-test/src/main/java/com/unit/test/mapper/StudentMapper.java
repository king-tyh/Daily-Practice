package com.unit.test.mapper;

import com.unit.test.bean.Student;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface StudentMapper {
    Student getByName(String name);

    List<Student> listStudent();

    void save(Student student);

    void batchSave(Iterable<Student> students);
}
