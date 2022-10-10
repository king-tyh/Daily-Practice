package com.test.myZkInterface.service;


import com.test.myZkInterface.model.Book;

public interface BookService {
    Book queryBookByName(String title);

    Integer queryBookCount();
}
