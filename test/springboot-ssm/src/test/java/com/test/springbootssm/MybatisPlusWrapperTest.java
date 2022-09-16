package com.test.springbootssm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.test.springbootssm.mapper.ChapterMapper;
import com.test.springbootssm.model.Chapter;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSsmApplication.class)
public class MybatisPlusWrapperTest {

    @Autowired
    ChapterMapper chapterMapper;

    @Test
    public void testSelectList() {
        //查询章节名包含神，content_id在50以内，按照book_id升序排列，book_id相同按照content_id降序排列
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", "神")
                .lt("content_id", 50)
                .orderByAsc("book_id")
                .orderByDesc("content_id");
        chapterMapper.selectList(queryWrapper);
    }

    @Test
    public void testDelete() {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "");
        int result = chapterMapper.delete(queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void testUpdate1() {
        //修改（title包含神，content_id在50以内）或（title为”“）
        Chapter chapter = new Chapter();
        chapter.setTitle("第");
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", "神")
                .lt("content_id", 50)
                .or()
                .eq("title", "");
        int result = chapterMapper.update(chapter, queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void testUpdate2() {
        //修改 title包含神 并且 （content_id在50以内 或 title为”“）
        //lambda中的条件优先执行
        Chapter chapter = new Chapter();
        chapter.setTitle("第x章");
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", "神")
                .and(i->i.lt("content_id",50).or().eq("title",""));

        int result = chapterMapper.update(chapter, queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void testUpdate3(){
        //修改 title包含神 并且 （content_id在50以内 或 title为”“）
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("title","神")
                        .and(i->i.lt("content_id",50).or().eq("title",""));
        updateWrapper.set("title","第三章");
        chapterMapper.update(null,updateWrapper);
    }

    @Test
    public void testSelectList2(){
        //部分字段查询：id，title，content_id
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","content_id");
        chapterMapper.selectList(queryWrapper);
    }

    @Test
    public void test(){
        //子查询查询 book_id>=2并且content_id>3的
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("content_id",3)
                .inSql("book_id","select id from book where id>=2");
        chapterMapper.selectList(queryWrapper);
    }

    @Test
    public void test2(){
        String str = "3";
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(str), Chapter::getTitle, str);
        chapterMapper.selectList(lambdaQueryWrapper);
    }

}
