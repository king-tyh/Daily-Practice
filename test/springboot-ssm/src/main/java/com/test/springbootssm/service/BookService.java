package com.test.springbootssm.service;


import com.test.springbootssm.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    List<Book> getBookByTitle(String title);

    boolean insertBook(Book book);
}
