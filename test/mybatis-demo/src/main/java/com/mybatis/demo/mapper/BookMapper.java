package com.mybatis.demo.mapper;

import com.mybatis.demo.model.Book;

public interface BookMapper {
    public int insertBook(Book book);

    public int deleteBookById(Integer id);
}
