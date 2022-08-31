package com.test.spring.test;

import com.test.spring.DAO.BookDaoImpl;
import com.test.spring.DAO.TeacherDaoImpl;
import com.test.spring.proxy.MyStaticProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        TeacherDaoImpl teacherDao = (TeacherDaoImpl) context.getBean("teacherDaoImpl");
        teacherDao.delete();
    }

}
