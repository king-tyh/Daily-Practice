package Liaoxuefeng;

import lombok.Data;

import java.lang.reflect.Field;

public class REFLECTIONFIELD {

    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));

        Student stu = new Student(64,2);
        //获取stu的score字段值;
        Class cla = stu.getClass();
        //Field field = cla.getField("score");
        //System.out.print(field.get(stu));
        Field field = cla.getDeclaredField("grade");
        field.setAccessible(true);  //访问非public权限的field前要设置setAccessible为True
        field.set(stu,2);
        System.out.print(stu);
    }
}

@Data
class Student extends Person {
    public int score;
    private int grade;

    public Student(int score,int grade){
        this.score = score;
        this.grade = grade;
    }
}

class Person {
    public String name;
}