package com.mybatis.demo.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.demo.model.Book;
import com.mybatis.demo.utils.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BookMapperTest {

    @Test
    public void insertBook() {

        try {
            //加载mybatis配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            SqlSessionFactory factory = builder.build(is);
            //会话(连接),表示mybatis和数据库之间的会话
            SqlSession sqlSession = factory.openSession();
            //通过会话获取Mapper对象
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            System.out.println(BookMapper.class);
            Book book = new Book(3, "我在精神病院学斩神", "简介：", "三九音域", "奇幻", "复苏");
            int i = bookMapper.insertBook(book);
            sqlSession.commit();
            assertEquals(i, 1);
            System.out.println(book);
        } catch (IOException e) {
            System.out.println("e");
        }
    }

    @Test
    public void testDeleteById() {
        try {
            //加载mybatis配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            SqlSessionFactory factory = builder.build(is);
            //会话(连接),表示mybatis和数据库之间的会话
            SqlSession sqlSession = factory.openSession();
            //通过会话获取Mapper对象
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            int i = bookMapper.deleteBookById(3);
            sqlSession.commit();
            assertEquals(i, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            Book book = new Book();
            int i = bookMapper.updateBook(new Book(3, "我在精神病院学斩神", "简介：林七夜", "三九音域", "奇幻", "复苏"));
            sqlSession.commit();
            assertEquals(i, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListBooks() {
        try {
            //加载mybatis配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            SqlSessionFactory factory = builder.build(is);
            //会话(连接),表示mybatis和数据库之间的会话
            SqlSession sqlSession = factory.openSession();
            //通过会话获取Mapper对象
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            List<Book> list = bookMapper.listBooks();
            assertNotNull(list);
            for (Book b : list)
                System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListBooksByPage(){
        BookMapper bookMapper = MyBatisUtil.getMapper(BookMapper.class);
        PageHelper.startPage(2,2);
        List<Book> list = bookMapper.listBooks();
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }
}
