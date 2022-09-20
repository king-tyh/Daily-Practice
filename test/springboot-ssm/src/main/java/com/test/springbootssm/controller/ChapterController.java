package com.test.springbootssm.controller;

import com.test.springbootssm.mapper.ChapterMapper;
import com.test.springbootssm.model.Book;
import com.test.springbootssm.model.Chapter;
import com.test.springbootssm.vo.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/chapter")
@RestController
@Api(tags = "章节信息管理")
public class ChapterController {
    Logger log = LoggerFactory.getLogger(ChapterController.class);
    @Autowired(required = false)
    ChapterMapper chapterMapper;

    @ResponseBody
    @GetMapping("/listChapter")
    @ApiOperation(value = "获取章节信息")
    public WebResponse listChapter(){
        WebResponse response = new WebResponse();
        response.setMsg("获取章节信息失败");
        response.setCode(1);
        try{
            List<Chapter> chapters = chapterMapper.selectList(null);
            if (chapters!=null){
                response.setMsg("获取小说信息成功");
                response.setCode(0);
                response.setData(chapters);
            }
        }catch (Exception e){
            log.error("listBooks ERROR: {1}",e);
        }
        return response;
    }
}
