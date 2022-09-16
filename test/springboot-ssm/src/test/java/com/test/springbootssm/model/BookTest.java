package com.test.springbootssm.model;

import com.test.springbootssm.SpringbootSsmApplication;
import com.test.springbootssm.mapper.BookMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSsmApplication.class)
public class BookTest {
    @Resource
    private BookMapper bookMapper;

    @Test
    public  void testInsertBook() throws Exception{
        Book book = new Book(0,"恐怖复苏","简介: 恐怖复苏","三石","灵异","末日");
        bookMapper.insertBook(book);
        //Assert.assertEquals(book.getId(),3);
    }

    @Test
    public  void testListBooks() throws Exception{
        List<Book> books = bookMapper.listBooks();
        for(Book book:books)
            System.out.println(book);
    }
}
