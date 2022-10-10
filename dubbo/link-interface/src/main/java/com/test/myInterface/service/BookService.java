package com.test.myInterface.service;

import com.test.myInterface.model.Book;

public interface BookService {
    Book queryBookByName(String title);

    Integer queryBookCount();
}
