package com.mybatis.demo.mapper;

import com.mybatis.demo.model.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;



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
            int i = bookMapper.insertBook(new Book(3,"我在精神病院学斩神","简介：","三九音域","奇幻","复苏"));
            sqlSession.commit();
            System.out.println("i: " + i);
        } catch (IOException e) {
            System.out.println("e");
        }
    }
}