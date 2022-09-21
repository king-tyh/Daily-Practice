package com.mybatis.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Chapter extends BaseModel {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private Integer bookId;

    private Integer contentId;

    private Integer isDelete;

    private Integer words;

    private Date createTime;

    private Date updateTime;

}
