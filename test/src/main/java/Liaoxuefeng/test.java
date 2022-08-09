package Liaoxuefeng;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
//        File f = new File(test.class.getResource("/insertData.txt").getFile());
        File f = new File(test.class.getResource("/insertData.txt").getFile());
        System.out.println(f.exists());
        System.out.println(Arrays.toString("this is a test".split(" ")));
    }
}
