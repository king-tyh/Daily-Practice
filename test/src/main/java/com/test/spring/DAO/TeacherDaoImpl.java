package com.test.spring.DAO;
import org.springframework.stereotype.Component;

@Component
public class TeacherDaoImpl{
    public void insert() {
        System.out.println("----insert---teacher");
    }

    public void delete() {
        System.out.println("----delete---teacher");
    }

    public void update() {
        System.out.println("----update---teacher");
    }

}
