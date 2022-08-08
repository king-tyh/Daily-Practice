package Liaoxuefeng;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TREEMAP {
    public static void main(String[] args) {
        String a = "123";
        String b = new String("123");
        System.out.print(a.compareTo(b));

        //在TreeMapTest1中实现Comparable接口中的compareTo方法，会按照该方法对Key进行排序
        // ps当Key相等时必须返回0
        Map<TreeMapTest1,Integer> map1 = new TreeMap<>();
        map1.put(new TreeMapTest1("li", 60),1);
        map1.put(new TreeMapTest1("zhang", 90),3);
        map1.put(new TreeMapTest1("dong", 70),2);
        for(TreeMapTest1 t: map1.keySet()){
            System.out.println(t);
        }


        //TreeMap会对Key按照创建TreeMap时的Comparator中的compare方法进行排序，
        // ps：就算TreeMapTest1中实现了Comparable接口中的compareTo方法也会被创建时的覆盖
        Map<TreeMapTest1, Integer> map2 = new TreeMap<>(new Comparator<TreeMapTest1>(){
            //用2比1实现逆序
            public int compare(TreeMapTest1 t1,TreeMapTest1 t2){
                return t2.name.compareTo(t1.name);
            }
        });
        map2.put(new TreeMapTest1("li", 60),1);
        map2.put(new TreeMapTest1("zhang", 90),3);
        map2.put(new TreeMapTest1("dong", 70),2);
        for(TreeMapTest1 t: map2.keySet()){
            System.out.println(t);
        }
    }
}

class TreeMapTest1 implements Comparable<TreeMapTest1> {
    String name;
    int score;

    TreeMapTest1(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("{name: %s, score: %d}", name,score);
    }

    @Override
    public int compareTo(TreeMapTest1 o) {
        return name.compareTo(o.name);
    }

}

//class TreeMapTest2 {
//    String name;
//    int score;
//
//    @Override
//    public String toString() {
//        return "{name: " + name + ", score: " + score + "}";
//    }
//
//    TreeMapTest2(String name, int score) {
//        this.name = name;
//        this.score = score;
//    }
//}