package Liaoxuefeng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIST {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        Integer[] arr = {1,2,3,4,5,};
        // asList生成的是不可变对象，不能增加删除元素
        List<Integer> list2 = Arrays.asList(arr);
        for(Integer i:list2){
            System.out.println(i);
        }
    }





}


