package Liaoxuefeng;

import java.lang.reflect.Field;

public class FANXING {
    public static void main(String[] args) throws NoSuchFieldException,IllegalAccessException{
        FanXingTest test = new FanXingTest("li");
        Field field = test.getClass().getDeclaredField("score");
        field.setAccessible(true);
        System.out.println(field.get(test).getClass());
        test = FanXingTest.create(123);
        System.out.println(field.get(test).getClass());
    }
}

class FanXingTest<T>{
    private T score;
    FanXingTest(T score){
        this.score = score;
    }

    public static <K> FanXingTest<K> create(K first){
        return new FanXingTest<K>(first);
    }
}