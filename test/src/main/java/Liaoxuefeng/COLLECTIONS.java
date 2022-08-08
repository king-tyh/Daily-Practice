package Liaoxuefeng;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class COLLECTIONS {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("apple");
        list.add("orange");
        list = Collections.unmodifiableList(list);
        //list.add("banana");这行代码执行会报错，因为list已经变成不可变List
        System.out.print(list);
    }
}
