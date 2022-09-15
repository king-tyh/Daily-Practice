package com.test.springbootssm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * Book实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("book")
public class Book {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * tittle
     */
    @TableField(value = "title")
    private String title;
    /**
     * desc
     */
    @TableField(value = "desc")
    private String desc;
    /**
     * author
     */
    @TableField(value = "author")
    private String author;
    /**
     * novel_type
     */
    @TableField(value = "novel_type")
    private String novelType;
    /**
     * tags
     */
    @TableField(value = "tags")
    private String tags;
}
