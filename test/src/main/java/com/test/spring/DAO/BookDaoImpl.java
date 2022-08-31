package com.test.spring.DAO;

import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BaseDao {
    @Override
    public void insert() {
        System.out.println("----insert---book");
    }

    @Override
    public void delete() {
        System.out.println("----delete---book");
    }

    @Override
    public void update() {
        System.out.println("----update---book");
    }
}
