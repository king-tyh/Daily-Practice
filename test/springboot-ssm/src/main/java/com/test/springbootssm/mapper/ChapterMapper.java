package com.test.springbootssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.springbootssm.model.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
    /**
     * 通过book_id查询章节信息并分页
     * @param page MyBatis-Plus提供的分页对象，必须位于第一个参数
     * @param bookId
     * @return
     */
    Page<Chapter> selectPageVo(@Param("page")Page page,@Param("bookId")Integer bookId);
}
