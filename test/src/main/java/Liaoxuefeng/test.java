package Liaoxuefeng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.*;

public class test {
    public static void main(String[] args) {

        System.out.println("9991ds".matches("9{4}.*"));
        Map<String,String> map = new HashMap<>();
        map.put("9991ds","111");
        System.out.println(map.get("9991ds"));
        String s = "5";
        s.replaceAll("%/%","25");
        System.out.println(s);
        MyData m = new MyData("Bob","english",54);
        System.out.println(JSONObject.toJSONString(m));
        String js = "{\"course\":\"english\",\"name\":\"Alice\",\"score\":3}";
        JSONObject jo = new JSONObject();
        jo.put("course","english");
        jo.put("score",34);
        jo.put("score",56);
        jo.put("name","Mark");
        jo.remove("name");
        System.out.println(jo.keySet());
        MyData m2 = JSONObject.parseObject(js,MyData.class);
        System.out.println(JSONObject.toJSONString(m2));

        System.out.println(JSONObject.toJSONString("sdsa"));


    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class MyData{
    private String name;
    private String course;
    private int score;
}
