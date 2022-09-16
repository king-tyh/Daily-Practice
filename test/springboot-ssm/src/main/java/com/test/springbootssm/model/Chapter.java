package com.test.springbootssm.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Chapter {
    private Long id;

    private String title;

    private int bookId;

    private int contentId;

    private int isDelete;

    private int words;

    private Date createTime;

    private Date updateTime;

}
