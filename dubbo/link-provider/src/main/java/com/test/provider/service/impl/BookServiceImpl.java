package com.test.provider.service.impl;


import com.test.myInterface.model.Book;
import com.test.myInterface.service.BookService;

public class BookServiceImpl implements BookService {
    @Override
    public Book queryBookByName(String title) {
        Book book = new Book();
        System.out.println("queryBookByName");
        book.setTitle(title);
        book.setDesc("desc");
        book.setAuthor("t c t d");
        System.out.println(book);
        return book;
    }

    @Override
    public Integer queryBookCount() {
        return 3;
    }
}
