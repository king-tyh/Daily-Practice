package com.test.springbootssm.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String exceptionHandler(Exception e){
        String message = e.toString();
        log.error("---------------{}",message);
        //根据不同的异常进行不同的处理
        return message;
    }
}
