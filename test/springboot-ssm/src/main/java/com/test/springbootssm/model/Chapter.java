package com.test.springbootssm.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@ApiModel(value="章节")
@TableName("chapter")
public class Chapter extends BaseModel{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "title", value = "标题", required = true,example = "第一章：xxx")
    @TableField("title")
    private String title;

    @ApiModelProperty(name = "bookId", value = "小说id", required = true,example = "1")
    @TableField("book_id")
    private Integer bookId;

    @ApiModelProperty(name = "content_id", value = "内容id", required = true,example = "1")
    @TableField("content_id")
    private Integer contentId;

    @ApiModelProperty(name = "isDelete", value = "逻辑删除（0:存在,1:已删除）", example = "0")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(name = "words", value = "字数", example = "1")
    @TableField("words")
    private Integer words;

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "1")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间", example = "1")
    @TableField(value = "update_time")
    private Date updateTime;

}
