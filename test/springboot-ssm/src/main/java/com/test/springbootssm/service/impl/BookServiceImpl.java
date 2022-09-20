package com.test.springbootssm.service.impl;

import com.test.springbootssm.controller.ChapterController;
import com.test.springbootssm.mapper.BookMapper;
import com.test.springbootssm.model.Book;
import com.test.springbootssm.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("bookService")
public class BookServiceImpl implements BookService {
    Logger log = LoggerFactory.getLogger(BookService.class);
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
    public List<Book> getBookByTitle(String title) {
        List<Book> books = null;
        try{
            log.info("查询book表: getBookByTitle({})",title);
            books = bookMapper.queryBookByTitle(title);
        }catch (Exception e){
            log.error("查询book表出错: ",e);
        }
        return books;
    }

    @Override
    public boolean insertBook(Book book) {
        boolean result=false;
        try{
            log.info("插入book表: insertBook");
            bookMapper.insertBook(book);
            result = true;
        }catch (Exception e){
            log.error("插入book表出错: ",e);
        }
        return result;
    }
}
