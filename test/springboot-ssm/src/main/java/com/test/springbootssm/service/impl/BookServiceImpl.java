package com.test.springbootssm.service.impl;

import com.test.springbootssm.mapper.BookMapper;
import com.test.springbootssm.model.Book;
import com.test.springbootssm.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired(required = false)
    private BookMapper bookMapper;

    @Override
    public List<Book> getBooks() {
        List<Book> books = null;
        try{
            log.info("查询book表: getBooks");
            books = bookMapper.listBooks();
        }catch (Exception e){
            log.error("查询book表出错: ",e);
        }
        return books;
    }

    @Override
    public Book getBookByTitle(String title) {
        Book book = null;
        try{
            log.info("查询book表: getBookByTitle{}",title);
            book = bookMapper.queryBookByTitle(title);
        }catch (Exception e){
            log.error("查询book表出错: ",e);
        }
        return book;
    }

    @Override
    public boolean insertBook(Book book) {
        try{
            log.info("插入book表: insertBook");
            bookMapper.insertBook(book);
        }catch (Exception e){
            log.error("插入book表出错: ",e);
        }
        return true;
    }
}
