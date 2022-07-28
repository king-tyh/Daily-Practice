package Liaoxuefeng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class INPUTSTREAM {
    public static void main(String[] args) throws IOException {
        //一个字节一个字节读
        try(InputStream input = new FileInputStream(".//INPUTSTREAMTEST.txt")){
            int n;
            while((n=input.read())!=-1){
                System.out.println((char)n);
            }
        }

        //一次读取多个字节
        try(InputStream input = new FileInputStream(".//INPUTSTREAMTEST.txt")){
            byte[] buffer = new byte[1000];
            int n;
            while((n = input.read(buffer))!=-1){
                System.out.println("read " + n + "bytes");
            }
        }
    }
}
