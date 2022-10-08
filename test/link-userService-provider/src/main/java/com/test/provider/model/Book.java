package com.test.provider.model;


/**
 * Book实体类
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNovelType() {
        return novelType;
    }

    public void setNovelType(String novelType) {
        this.novelType = novelType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * novel_type
     */

    private String novelType;
    /**
     * tags
     */

    private String tags;
}
