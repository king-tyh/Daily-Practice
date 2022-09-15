package com.test.springbootssm.mystarter;

import com.org.hello.HelloTemplate;
import com.test.springbootssm.SpringbootSsmApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSsmApplication.class)
public class HelloTemplateTest {

    @Resource
    private HelloTemplate helloTemplate;

    @Test
    public void testMethod(){
        helloTemplate.add();
    }

}
