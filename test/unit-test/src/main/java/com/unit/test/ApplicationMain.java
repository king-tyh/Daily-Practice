package com.unit.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述：
 *
 * @author July Yang
 * @date 2022/8/28
 * @version 1.0.0
 */
@Slf4j
@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) {
        log.info("starting................");
        SpringApplication.run(ApplicationMain.class, args);
        log.info("start end................");
    }
}
