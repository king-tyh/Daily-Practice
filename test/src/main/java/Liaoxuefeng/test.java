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
        List<MyData> t = new ArrayList<>();
        MyData m1 = new MyData("course").setName("123");
        MyData m2 = new MyData("course").setName("123");
        MyData m3 = new MyData("course").setName("123");
        t.add(m1);
        t.add(m2);
        t.add(m3);
        Object o = t;
        System.out.println(new Date());


    }
}

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
class MyData {
    private String name;
    private final String course;
    private int score;
    @Singular("boo")
    private List<String> book;

}
