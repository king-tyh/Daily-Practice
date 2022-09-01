package com.test.spring.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TxManager {
    @Before("execution(* com.test.spring.DAO.*.*(..))")
    public void begin() {
        System.out.println("-----开启事务");
    }

    @After("execution(* com.test.spring.DAO.*.*(..))")
    public void commit() {
        System.out.println("-----提交事务");
    }

    @Pointcut("execution(* com.test.spring.DAO.*.*(..))")
    public void pc(){}
    @Around("pc()")
    public Object runningTime(ProceedingJoinPoint point) throws Throwable {
        long time1 = System.currentTimeMillis();
        Object v = point.proceed();
        long time2 = System.currentTimeMillis();
        System.out.println("------time:"+(time2-time1));
        return v;
    }

}
