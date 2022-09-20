package com.test.springbootssm.controller;

import com.test.springbootssm.model.Book;
import com.test.springbootssm.service.BookService;
import com.test.springbootssm.vo.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
@RestController
@Slf4j
@Api(tags = "小说信息管理")
public class BookController {

    Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @ResponseBody
    @RequestMapping(value = "/listBooks", method = {RequestMethod.GET})
    @ApiOperation(value = "获取小说信息")
    public WebResponse listBooks() {
        WebResponse response = new WebResponse();
        response.setMsg("获取小说信息失败");
        response.setCode(1);
        try {
            List<Book> books = bookService.getBooks();
            if (books != null) {
                response.setMsg("获取小说信息成功");
                response.setCode(0);
                response.setData(books);
            }
        } catch (Exception e) {
            log.error("listBooks ERROR: {1}", e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/addBook", method = {RequestMethod.POST})
    @ApiOperation(value = "添加小说")
    public WebResponse addBook(@RequestBody Book book) {
        WebResponse response = new WebResponse();
        response.setMsg("添加小说失败");
        response.setCode(1);
        try {
            boolean result = bookService.insertBook(book);
            if (result) {
                response.setMsg("添加小说成功");
                response.setCode(0);
                response.setData(book);
            }
        } catch (Exception e) {
            log.error("addBook ERROR: {1}", e);
        }
        return response;
    }

    @ResponseBody
    @GetMapping(value = "/getBookByTitle")
    @ApiOperation(value = "通过书名获取小说信息")
    @ApiImplicitParam(name = "title", paramType = "query", value = "书名", dataType = "String", example = "斗破苍穹")
    public WebResponse getBookByTitle(@RequestParam String title) {
        WebResponse response = new WebResponse();
        response.setMsg("获取小说失败");
        response.setCode(1);
        try {
            List<Book> books = bookService.getBookByTitle(title);
            if (books != null) {
                response.setMsg("获取小说成功");
                response.setCode(0);
                response.setData(books);
            }
        } catch (Exception e) {
            log.error("getBookByTitle ERROR: {1}", e);
        }
        return response;
    }

    @GetMapping("/test")
    @ApiOperation(value = "test")
    public String test(Model model, String title) {
        System.out.println("--------->>>test");
        List<Book> books = bookService.getBookByTitle(title);
        model.addAttribute("str", "从控制器传递到页面的数据");
        model.addAttribute("book", books);
        model.addAttribute("color", "red");

        List<Book> allBooks = bookService.getBooks();
        model.addAttribute("books", allBooks);
        return "index";
    }

}
