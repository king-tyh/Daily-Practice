package com.test.spring.work;

import com.csvreader.CsvReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class work {

    public static void main(String[] args) throws IOException {
        List<String> ysData = new ArrayList<>();
        List<String> prodData = new ArrayList<>();
        List<String> deleteYs = new ArrayList<>();
        List<String> addYs = new ArrayList<>();
        read(ysData,"src/main/resources/ys.csv");
        read(prodData,"src/main/resources/prod.csv");
        int i=0;
        for(String data:ysData){
            if(!prodData.contains(data))
                deleteYs.add(data);
        }
        for(String data:prodData){
            if(!ysData.contains(data))
                addYs.add(data);
        }
        for(String s:addYs)
            System.out.println(++i + ": " + s);



    }


    public static void read(List<String> data,String file) throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集

        CsvReader csvReader = new CsvReader(file, ',', Charset.forName("UTF-8"));

        csvReader.readHeaders();
        // 读取每行的内容
        while (csvReader.readRecord()) {
            data.add(csvReader.get("appId"));
        }
    }

    /*public static void read() throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集

        CsvReader csvReader = new CsvReader("src/main/resources/ys.csv", ',', Charset.forName("UTF-8"));

        // 如果你的文件没有表头，这行不用执行
        // 这行不要是为了从表头的下一行读，也就是过滤表头
        csvReader.readRecord();
        String[] params = csvReader.getValues();
        for(String param:params) {
            System.out.println(param);
        }
        // 读取每行的内容
        while (csvReader.readRecord()) {
            AiAppIdentify aiAppIdentify = new AiAppIdentify();
            for(String param:params){
                if (csvReader.get(param)!=null && !Objects.equals(csvReader.get(param), ""))
                    setFieldValueByName(aiAppIdentify,param,csvReader.get(param));
            }
            //System.out.println(aiAppIdentify);
            // 获取内容的两种方式
            // 1. 通过下标获取
            *//*System.out.println();*//*
        }
    }*/


    /*public static void setFieldValueByName(Object obj, String fieldName, Object value){
        try {
            // 获取obj类的字节文件对象
            Class c = obj.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(obj, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }*/
}
