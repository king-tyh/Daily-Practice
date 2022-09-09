package com.unit.test.bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Student {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 各个科目分数
     */
    private List<SubjectScore> subjectScores;

    /**
     * 总分
     */
    private Double sumScore;

    /**
     * 性别，1-男，2-女
     */
    private Integer gender;

    /**
     * 状态
     */
    private Integer state;

    public List<SubjectScore> getSubjectScores() {
        if (subjectScores != null && subjectScores.size() != 0) {
            subjectScores.sort(defaultSort());
        }
        return subjectScores;
    }

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 网器设备简化模型
     */
    @Getter
    @Setter
    public static class SubjectScore {
        /**
         * 科目
         */
        private Subject subject;

        /**
         * 分数
         */
        private Double score;
    }

    /**
     * 根据科目排序
     *
     * @return 排序
     */
    private Comparator<SubjectScore> defaultSort() {
//        return Comparator.comparing(Student::getSumScore).reversed();
        return Comparator.comparing(SubjectScore::getSubject, Comparator.nullsLast(Subject::compareTo)).reversed();
    }

    public enum Subject implements Comparator<Integer> {
        /**
         * 数学
         */
        MATH(1),
        /**
         * 语文
         */
        CHINESE(2),
        /**
         * 英语
         */
        ENGLISH(3);

        private final int order;

        Subject(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return getOrder();
        }
    }
}
