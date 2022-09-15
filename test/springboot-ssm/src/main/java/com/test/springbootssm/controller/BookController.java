package com.test.springbootssm.controller;

import com.test.springbootssm.model.Book;
import com.test.springbootssm.service.BookService;
import com.test.springbootssm.vo.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/test")
@Controller
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;

    @ResponseBody
    @RequestMapping("/listBooks")
    public BookResponse listBooks(){
        BookResponse response = new BookResponse();
        response.setMsg("获取小说信息失败");
        response.setCode(1);
        try{
            List<Book> books = bookService.getBooks();
            if (books!=null){
                response.setMsg("获取小说信息成功");
                response.setCode(0);
                response.setData(books);
            }
        }catch (Exception e){
            log.error("listBooks ERROR: {1}",e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/addBook")
    public BookResponse addBook(Book book){
        BookResponse response = new BookResponse();
        response.setMsg("添加小说失败");
        response.setCode(1);
        try{
            boolean result = bookService.insertBook(book);
            if (result){
                response.setMsg("添加小说成功");
                response.setCode(0);
                response.setData(book);
            }
        }catch (Exception e){
            log.error("addBook ERROR: {1}",e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/getBookByTitle")
    public BookResponse getBookByTitle(@RequestParam String title){
        BookResponse response = new BookResponse();
        response.setMsg("获取小说失败");
        response.setCode(1);
        try{
            Book book = bookService.getBookByTitle(title);
            if (book!=null){
                response.setMsg("获取小说成功");
                response.setCode(0);
                response.setData(book);
            }
        }catch (Exception e){
            log.error("getBookByTitle ERROR: {1}",e);
        }
        return response;
    }

    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("--------->>>test");
        model.addAttribute("str","从控制器传递到页面的数据");
        return "index";
    }

}
