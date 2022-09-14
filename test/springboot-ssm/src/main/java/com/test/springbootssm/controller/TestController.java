package com.test.springbootssm.controller;

import com.test.springbootssm.model.Book;
import com.test.springbootssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/test")
@Controller
public class TestController {
    @Resource
    BookService bookService;

    @ResponseBody
    @RequestMapping("/listBooks")
    public List<Book> test(){
        return bookService.getBooks();
    }
}
