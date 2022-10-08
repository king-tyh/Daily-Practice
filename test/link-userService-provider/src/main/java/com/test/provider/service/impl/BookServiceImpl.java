package com.test.provider.service.impl;


import com.test.provider.model.Book;
import com.test.provider.service.BookTestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("bookTestService")
public class BookServiceImpl implements BookTestService {

    @Override
    public List<Book> getBookByTitle(String title) {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        book.setTitle(title);
        book.setDesc("简介：");
        book.setAuthor("天蚕土豆");
        bookList.add(book);
        return bookList;
    }
}
