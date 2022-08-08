package Liaoxuefeng;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HASHMAP {
    public static void main(String[] args) {
        Map<MapTest,Integer> map = new HashMap<>();
        MapTest mapTest = new MapTest("李","四",20);
        MapTest mapTest2 = new MapTest("李","四",20);
        //如果没有重写equals方法，此处比较的值会为false
        System.out.println(mapTest.equals(mapTest2));
        map.put(mapTest,1);
        System.out.print(mapTest.hashCode());
    }
}

class MapTest {
    String firstName;
    String lastName;
    int age;

    MapTest(String firstName,String lastName,int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    //重写equals方法
    @Override
    public boolean equals(Object o) {
        //首先判断是不是MapTest类型
        if (o instanceof MapTest) {
            MapTest p = (MapTest) o;
            //按照firstName，lastName，age三个字段判断是不是相等
            return Objects.equals(firstName, p.firstName) && Objects.equals(lastName, p.lastName) && age == p.age;
        }
        return false;
    }

    //重写hashCode方法
    @Override
    public int hashCode() {
        int h = 0;
        h = firstName.hashCode();
        //*31为了尽量将不同的MapTest实例均匀分布到int范围
        h = 31 * h + lastName.hashCode();
        h = 31 * h + age;
        return h;
    }
}