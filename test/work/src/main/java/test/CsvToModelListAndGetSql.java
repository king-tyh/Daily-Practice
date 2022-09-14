package test;
import com.csvreader.CsvReader;
import model.AiAppIdentify;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvToModelListAndGetSql {

    public static void main(String[] args) throws IOException {
        List<String> ysDataId = new ArrayList<>();
        List<String> prodDataId = new ArrayList<>();
        List<String> deleteYsId = new ArrayList<>();
        List<String> addYsId = new ArrayList<>();
        List<String> params = new ArrayList<>();
        List<AiAppIdentify> addData = new ArrayList<>();
        readId(ysDataId, "work/src/main/resources/ys.csv");
        readId(prodDataId, "work/src/main/resources/prod.csv");
        int i = 0;
        for (String data : ysDataId) {
            if (!prodDataId.contains(data))
                deleteYsId.add(data);
        }
        for (String data : prodDataId) {
            if (!ysDataId.contains(data))
                addYsId.add(data);
        }

        //删除验收有生产没有的 sql
        /*System.out.println("DELETE FROM `ai_app_identify` WHERE id in (");
        for(String s:deleteYsId)
            System.out.println("\"" + s + "\"" + ",");*/

        //插入生产有验收没有的 sql
        System.out.print("INSERT INTO `ai_app_identify`(");
        read(addData,addYsId,params);


    }


    public static void readId(List<String> data, String file) throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集

        CsvReader csvReader = new CsvReader(file, ',', Charset.forName("UTF-8"));

        csvReader.readHeaders();
        // 读取每行的内容
        while (csvReader.readRecord()) {
            data.add(csvReader.get("appId"));
        }
    }

    public static void read(List<AiAppIdentify> addData, List<String> addId, List<String> allParam) throws IOException {

        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvReader csvReader = new CsvReader("work/src/main/resources/prod.csv", ',', Charset.forName("UTF-8"));

        // 如果你的文件没有表头，这行不用执行
        // 这行不要是为了从表头的下一行读，也就是过滤表头
        csvReader.readHeaders();
        String[] params = csvReader.getHeaders();
        for(String param:params)
        {
            System.out.print(param + ",");
        }
        System.out.print(")values \n");
        // 读取每行的内容
        while (csvReader.readRecord()) {
            if (addId.contains(csvReader.get("appId"))) {
                System.out.print("(");
                int i=0;
                for (String param : params) {
                    switch (param){
                        case "hasTts":
                        case "oathCheck":
                        case "asrNeedStatis":
                        case "nlpNeedStatis":
                        case "isDot":
                        case "status":
                        case "sort":
                        case "needDialog":
                        case "appType":
                        case "limitFlag":
                            System.out.print(csvReader.get(param));
                            break;
                        default:
                            if(!Objects.equals(csvReader.get(param), "null"))
                                System.out.print("\"" + csvReader.get(param) +  "\"");
                            else System.out.print(csvReader.get(param));

                    }
                    if(i < params.length-1) System.out.print(",");
                    else System.out.println("),");
                    i++;
                }
            }
        }
    }


    public static void setFieldValueByName(Object obj, String fieldName, Object value) {
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
    }

    public static String getFieldValueByName(Object obj, String fieldName) {
        String v = null;
        try {
            // 获取obj类的字节文件对象
            Class c = obj.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            v = (String) f.get(obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
}
