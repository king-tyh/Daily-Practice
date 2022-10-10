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
    BookService bookService;
    @RequestMapping(value = "/bookDetail")
    public String bookDetail(Model model, String title){
        Book book = bookService.queryBookByName(title);
        model.addAttribute("book",book);
        System.out.println("controller" + book);
        return "bookDetail";
    }
}
