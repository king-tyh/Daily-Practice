package com.test.spring.Bean;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    private String stuNum;
    private String stuName;
    private int stuAge;
    private Date enterDate;

    Student(String stuNum,int stuAge){
        this.stuNum = stuNum;
        this.stuAge = stuAge;
    }

    Student(String stuNum,String stuName){
        this.stuNum = stuNum;
        this.stuName = stuName;
    }


}
