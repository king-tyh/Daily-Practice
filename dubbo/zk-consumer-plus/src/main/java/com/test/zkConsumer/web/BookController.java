package com.test.zkConsumer.web;


import com.test.myZkInterface.model.Book;
import com.test.myZkInterface.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    @Autowired(required = false)
    BookService bookService1;

    @Autowired(required = false)
    BookService bookService2;

    @RequestMapping(value = "/bookDetail")
    public String bookDetail(Model model, String title){
        Book book = bookService1.queryBookByName(title);
        model.addAttribute("book",book);
        return "bookDetail";
    }

    @RequestMapping(value = "/bookDetail2")
    public String bookDetail2(Model model, String title){
        Book book = bookService2.queryBookByName(title);
        model.addAttribute("book",book);
        return "bookDetail";
    }
}
