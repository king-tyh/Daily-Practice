package com.test.spring.test;

import com.test.spring.DAO.TeacherDaoImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        TeacherDaoImpl teacherDao = (TeacherDaoImpl) context.getBean("teacherDaoImpl");
        teacherDao.delete();
    }

}
