package com.test.springbootssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springbootssm.model.Chapter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
}
