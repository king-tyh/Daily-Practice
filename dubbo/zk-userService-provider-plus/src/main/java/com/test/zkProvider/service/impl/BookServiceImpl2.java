package com.test.zkProvider.service.impl;

import com.test.myZkInterface.model.Book;
import com.test.myZkInterface.service.BookService;

public class BookServiceImpl2 implements BookService{
    @Override
    public Book queryBookByName(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setDesc("简介");
        book.setAuthor("天蚕土豆");
        return book;
    }

    @Override
    public Integer queryBookCount() {
        return 3;
    }
}
