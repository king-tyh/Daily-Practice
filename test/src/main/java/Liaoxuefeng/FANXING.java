package Liaoxuefeng;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class FANXING {
    public static void main(String[] args) throws NoSuchFieldException,IllegalAccessException{
        FanXingTest test = new FanXingTest("li");
        Field field = test.getClass().getDeclaredField("score");
        field.setAccessible(true);
        System.out.println(field.get(test).getClass()); //可以获取field的类型
        test = FanXingTest.create(123);
        System.out.println(field.get(test).getClass());

        System.out.println(test.getClass());//getClass只能够获取到FanXingTest,不能得到他的泛型类型

        //但子类可以获取继承的父类的泛型类型
        Class<FanXingChild> clazz = FanXingChild.class;
        Type t = clazz.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments();
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass);
        }
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

class FanXingChild extends FanXingTest<Integer>{
    FanXingChild() {
        super(2);
    }

}