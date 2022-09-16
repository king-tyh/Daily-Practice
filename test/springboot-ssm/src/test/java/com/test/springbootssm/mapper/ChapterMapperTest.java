package com.test.springbootssm.mapper;

import com.test.springbootssm.SpringbootSsmApplication;
import com.test.springbootssm.model.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = SpringbootSsmApplication.class)
@RunWith(SpringRunner.class)
public class ChapterMapperTest {
    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void testSelectList(){
        List<Chapter> chapters = chapterMapper.selectList(null);
        chapters.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        Chapter chapter = new Chapter();
        //chapter.setId(3);
        chapter.setContentId(3);
        chapter.setBookId(1);
        chapter.setTitle("第三章");
        chapter.setIsDelete(1);
        chapter.setWords(6000);
        chapterMapper.insert(chapter);
    }

    @Test
    public void testDeleteById(){
        int result = chapterMapper.deleteById(1570692277883076609L);
        System.out.println("result: " + result);
    }

}
