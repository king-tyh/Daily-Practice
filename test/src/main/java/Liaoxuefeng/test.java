package Liaoxuefeng;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Weekday a = Weekday.valueOf(2);
        Date date=new Date();
        System.out.println(date);
        long timestamp=date.getTime();
        System.out.println(timestamp);

        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        String timeText=format.format(timestamp);                                //获得带格式的字符串
        System.out.println(timeText);
    }
}
