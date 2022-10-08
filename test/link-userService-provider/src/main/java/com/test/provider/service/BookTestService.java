package com.test.provider.service;



import com.test.provider.model.Book;

import java.util.List;

public interface BookTestService {

    List<Book> getBookByTitle(String title);

}
