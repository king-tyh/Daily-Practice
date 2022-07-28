package Liaoxuefeng;

import java.io.File;
import java.io.IOException;

public class FILE {
    public static void main(String[] args) throws IOException {
        //若test.txt不存在则创建，若存在则删除
        File file = new File(".\\test.txt");
        boolean res = file.createNewFile();
        System.out.printf("创建文件是否成功: %s\n",res);
        if(!res){
            res = file.delete();
            System.out.printf("是否删除了文件: %s\n",res);
        }

        //输出当前目录所有子目录和文件
        File file2 = new File(".//");
        printAllFile(file2);
    }

    //默认参数，str=“”
    public static boolean printAllFile(File file){
        return printAllFile(file,"");
    }

    //利用递归输出所有子目录和子文件
    // 判断是子目录时先输出它的名字对其所有listFiles()进行递归调用,并且对分隔符加上两个空格
    public static boolean printAllFile(File file,String blank){
        if(!file.exists())
            return false;
        if(file.isFile())
            System.out.println(blank + file.getName());
        else if(file.isDirectory()){
            System.out.println(blank + file.getName() + "/");
            for(File f:file.listFiles()){
                printAllFile(f,blank+"  ");
            }
        }
        return true;
    }
}
