package com.test.springbootssm;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.springbootssm.mapper.ChapterMapper;
import com.test.springbootssm.model.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootSsmApplication.class)
@RunWith(SpringRunner.class)
public class MyBatisPlusPluginsTest {
    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void testSelectPage(){
        //第2页 每页3个
        Page<Chapter> page = new Page<>(2,3);
        chapterMapper.selectPage(page,null);
    }

    @Test
    public void testSelectPageVo(){
        Page<Chapter> page = new Page<>(2,3);
        chapterMapper.selectPageVo(page,1);
    }
}
