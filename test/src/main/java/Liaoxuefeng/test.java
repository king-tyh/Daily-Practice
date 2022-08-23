package Liaoxuefeng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.*;

public class test {
    public static void main(String[] args) {

        System.out.println("99991ds".matches("9{4}.*"));

        MyData m = new MyData("course");
        m.setName("123");
        System.out.println((Map<String,Object>)m);


    }
}

@Data
@RequiredArgsConstructor
class MyData {
    private String name;
    private final String course;
    private int score;
    @Singular("boo")
    private List<String> book;

    public String getName(){return this.name+"222";}
}
