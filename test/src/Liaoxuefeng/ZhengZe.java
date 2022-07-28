package Liaoxuefeng;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengZe {
    public static void main(String[] args) {
        String re = "\\d{3,4}-\\d{7,8}";
        for (String s : Arrays.asList("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : Arrays.asList("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");

        System.out.println("\n分组匹配##########");
        //分组匹配 "\\d{3,4}-[a-zA-Z]{3}\\d{2,3}" 分为\\d{3,4}  [a-zA-Z]{3} 和d{2,3}
        Pattern p = Pattern.compile("(\\d{3,4})-([a-zA-Z]{3})(\\d{2,3})");
        Matcher m = p.matcher("029-abc831");
        if(m.matches()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
            // System.out.println(m.group(4)); 报错 No group 4
            System.out.println(m.group(0));
        }else{
            System.out.println("匹配失败");
        }

        System.out.println("\n搜索字符串##########");
        //搜索字符串中的所有"\\d0\\d"
        String str = "10101230450";
        Pattern searchPattern = Pattern.compile("\\d0\\d");
        Matcher searchMatcher = searchPattern.matcher(str);
        while(searchMatcher.find()){
            System.out.println(str.substring(searchMatcher.start(),searchMatcher.end()));
        }

        System.out.println("\n替换字符串###########\n");
//        将所有“李四”替换为"王五"
//        String str2 = "李四今天早上去跑步遇见了张三，张三说李四跑的快";
//        System.out.print(str2.replaceAll("李四","王五"));
        //把所有0\d替换成\d0
        String str2 = "010112621407465014065742";
        System.out.println(str2.replaceAll("(0)(\\d)","$2$1"));
        str2 = "This ais as tests make abs sure you ...";
        System.out.println(str2.replaceAll("\\s([a-zA-Z]{4})\\s"," <b>$1</b> "));
    }
}

