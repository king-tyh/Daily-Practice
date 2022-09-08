package test;

import tool.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader();
        List<Map<String,String>> datas = new ArrayList<>();
        String[] fields = {};
        datas = reader.readData("work/src/main/resources/prod.csv",fields);
        for (Map<String,String> map:datas){
            System.out.println(map.values());
        }

    }
}
