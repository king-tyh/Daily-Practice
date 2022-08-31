package com.test.spring.DAO;

import com.test.spring.DAO.BaseDao;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements BaseDao {

    @Override
    public void insert() {
        System.out.println("----insert---student");
    }

    @Override
    public void delete() {
        System.out.println("----delete---student");
    }

    @Override
    public void update() {
        System.out.println("----update---student");
    }
}
