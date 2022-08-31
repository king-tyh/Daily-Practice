package com.test.spring.proxy;
import com.test.spring.DAO.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyStaticProxy {
    @Autowired
    @Qualifier("studentDaoImpl")
    BaseDao baseDao;

    public void insert() {
        begin();
        this.baseDao.insert();
        commit();
    }

    public void delete() {
        begin();
        this.baseDao.delete();
        commit();
    }

    public void update() {
        begin();
        this.baseDao.update();
        commit();
    }

    private void begin() {
        System.out.println("-----开启事务");
    }

    private void commit() {
        System.out.println("-----提交事务");
    }

}
