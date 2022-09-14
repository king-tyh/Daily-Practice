package com.test.springbootssm.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Book实体类
 */
@Data
@Alias("book")
public class Book {
    /**
     * id
     */
    private Integer id;
    /**
     * tittle
     */
    private String tittle;
    /**
     * desc
     */
    private String desc;
    /**
     * author
     */
    private String author;
    /**
     * novel_type
     */
    private String novelType;
    /**
     * tags
     */
    private String tags;
}
