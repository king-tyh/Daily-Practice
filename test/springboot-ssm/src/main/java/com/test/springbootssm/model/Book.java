package com.test.springbootssm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Book实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="小说")
@TableName("book")
public class Book extends BaseModel{
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * tittle
     */
    @ApiModelProperty(name = "title", value = "书名", required = true, example = "斗破苍穹")
    @TableField(value = "title")
    private String title;
    /**
     * desc
     */
    @ApiModelProperty(name = "desc", value = "简介", example = "简介：")
    @TableField(value = "desc")
    private String desc;
    /**
     * author
     */
    @ApiModelProperty(name = "author", value = "作者", required = true, example = "天蚕土豆")
    @TableField(value = "author")
    private String author;
    /**
     * novel_type
     */
    @ApiModelProperty(name = "novelType", value = "类型", example = "玄幻")
    @TableField(value = "novel_type")
    private String novelType;
    /**
     * tags
     */
    @ApiModelProperty(name = "tags", value = "标签", example = "热血")
    @TableField(value = "tags")
    private String tags;
}
