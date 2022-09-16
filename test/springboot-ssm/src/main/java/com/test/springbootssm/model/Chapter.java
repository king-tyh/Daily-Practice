package com.test.springbootssm.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("chapter")
public class Chapter {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("book_id")
    private Integer bookId;

    @TableField("content_id")
    private Integer contentId;

    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @TableField("words")
    private Integer words;

    @TableField("create_time")
    private Date createTime;

    @TableField(value = "update_time", update = "now()")
    private Date updateTime;

}
