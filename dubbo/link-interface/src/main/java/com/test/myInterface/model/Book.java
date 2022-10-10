package com.test.myInterface.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private String title;

    private String desc;

    private String author;

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
}
