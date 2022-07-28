package Liaoxuefeng;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class USEANNOTATION {
    public static void main(String[] args) {
        //在AnnotationTest类中使用注解Range限制name在10~20，city在0~10
        AnnotationTest annotationTest = new AnnotationTest("12345678910", "123456789");
    }
}

class AnnotationTest {
    @Range(min = 10, max = 20)
    public String name;

    @Range(max = 10)
    public String city;

    AnnotationTest(String name, String city) {
        this.name = name;
        this.city = city;
        try {
            check(this);
        } catch (IllegalArgumentException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    void check(AnnotationTest annotationTest) throws IllegalArgumentException, ReflectiveOperationException {
        //遍历annotationTest的每一个field
        for (Field field : annotationTest.getClass().getFields()) {
            //获取field的@Range
            Range range = field.getAnnotation(Range.class);
            //如果有@range修饰
            if (range != null) {
                //获取annotation的field的值
                Object value = field.get(annotationTest);
                //如果该field是String类型 ps:假设我们的@Range只对String类型生效
                if (value instanceof String) {
                    String s = (String) value;
                    //如果字符串超出@Range的限制
                    if (s.length() > range.max() || s.length() < range.min()) {
                        throw new IllegalArgumentException("Invalid field " + field.getName());
                    }
                }
            }
        }
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default 0;

    int max() default 255;
}