package com.test.zkProvider.service.impl;

import com.test.myZkInterface.model.Book;
import com.test.myZkInterface.service.BookService;

public class BookServiceImpl1 implements BookService{
    @Override
    public Book queryBookByName(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setDesc("desc");
        book.setAuthor("t c t d");
        return book;
    }

    @Override
    public Integer queryBookCount() {
        return 3;
    }
}
