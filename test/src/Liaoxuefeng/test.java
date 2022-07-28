package Liaoxuefeng;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String re = "^\\d\\D{3}\\d$";
        System.out.println("2ddw2".matches(re));
        System.out.println("addw2".matches(re));
        System.out.println("22df2".matches(re));
    }
}
