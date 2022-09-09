package com.test.springboot.demo.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document("user")
@Data
public class User implements Serializable {
    /**
     * Id:
     *  Spring Data提供的公共注解，用来描述主键
     *  在Spring Data MongoDB环境中，如果实体类的属性命名为id或_id，且这个属性为主键属性时，可以省略@Id注解
     */
    @Id
    @Field("_id")
    private Long id;

    /**
     * Field:
     *  描述类中属性与集合字段的映射关系
     *  不指定则默认为属性名
     */
    @Field("username")
    private String username;


    private String password;

    private String email;

    private List<String> courses;

}
