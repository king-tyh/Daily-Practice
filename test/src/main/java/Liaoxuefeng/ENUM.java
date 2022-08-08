package Liaoxuefeng;

import java.util.Scanner;

public class ENUM {
    public static void main(String[] args){
        Weekday day = Weekday.MON;
        switch (day){
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.print("Today is "+ day+ ". Work at office!");
                break;
            default:
                System.out.print("Today is "+ day+ ". Work at home!");
        }
    }
}

enum Weekday{
    MON(1,"星期一"),TUE(2,"星期二"),WED(3,"星期三"),THU(4,"星期四"),
    FRI(5,"星期五"),SAT(6,"星期六"),SUN(7,"星期日");
    public final int dayValue;
    private final String chinese;
    private Weekday(int dayValue,String chinese)
    {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString(){
        return this.chinese;
    }
}