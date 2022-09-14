package com.test.springbootssm.mapper;

import com.test.springbootssm.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BookMapper {
    void insertBook(Book book) throws Exception;

    List<Book> listBooks() throws Exception;

    Book queryBookByTitle(String title) throws Exception;
}
