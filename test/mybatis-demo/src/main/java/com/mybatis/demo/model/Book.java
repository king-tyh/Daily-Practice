package com.mybatis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * tittle
     */
    private String title;
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
