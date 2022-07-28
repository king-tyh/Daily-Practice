package Liaoxuefeng;

import java.util.HashMap;
import java.util.Map;


public class MAP {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put(null,55);//null可以作为K(也可以作为value)
        System.out.println(map.get(null));

        map.put("li",56);
        Integer score = map.put("li",66);//键值"li"已经存在，所以会返回对应V,然后改写成新V
        System.out.println(score);
        System.out.println(map.get("li"));

        //遍历key
        for(String key:map.keySet())
            System.out.println(key);

        //遍历key-value
        for(Map.Entry<String,Integer> entry:map.entrySet())
            System.out.println(entry.getKey() + " = " + entry.getValue());
    }
}
