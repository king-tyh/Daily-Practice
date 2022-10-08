package com.test.dubbo.web.controller;



import com.test.provider.model.Book;
import com.test.provider.service.BookTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookTestService bookTestService;

    @RequestMapping("/user")
    public String userDetail(Model model, String title){
        List<Book> bookList = bookTestService.getBookByTitle(title);
        model.addAttribute("bookList",bookList);
        return "bookDetail";
    }
}
