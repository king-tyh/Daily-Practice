package Liaoxuefeng;

import java.lang.reflect.Method;

public class REFLECTIONMETHOD {
    public static void main(String[] args){
        MethodTest stu = new MethodTest(60,"li");
        //获取stu的Class
        Class cla = stu.getClass();
        try {
            //获取cla的getScore的Method
            Method met = cla.getDeclaredMethod("getScore");
            //打印met的参数列表类型
            System.out.println(met.getParameterTypes());
            //因为getScore不是public所以要设置setAccessible为true
            met.setAccessible(true);
            //利用.invoke(对应类的实例对象,参数列表类型类...)调用该方法
            System.out.print(met.invoke(stu));
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}

class MethodTest{
    private int score;
    public String name;
    MethodTest(int score,String name){
        this.score = score;
        this.name = name;
    }
    private int getScore(){
        return this.score;
    }
}