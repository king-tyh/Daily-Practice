package com.mybatis.demo.mapper;

import com.mybatis.demo.model.Book;

import java.util.List;

public interface BookMapper {
    int insertBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    List<Book> listBooks();

}
