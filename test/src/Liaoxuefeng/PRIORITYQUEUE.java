package Liaoxuefeng;

import java.util.PriorityQueue;
import java.util.Queue;

public class PRIORITYQUEUE {
    public static void main(String[] args) {
        Queue<PriorityQueueTest> queue = new PriorityQueue<>();
        queue.add(new PriorityQueueTest("li", 80));
        queue.add(new PriorityQueueTest("zhang", 90));
        queue.add(new PriorityQueueTest("wang", 92));

        while (queue.size() != 0) {
            System.out.println(queue.poll());
        }
    }
}


class PriorityQueueTest implements Comparable<PriorityQueueTest> {
    String name;
    int score;

    PriorityQueueTest(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //重写compareTo方法，按照score从大到小
    @Override
    public int compareTo(PriorityQueueTest p) {
        if (score == p.score)
            return 0;
        return score < p.score ? 1 : -1;
    }

    @Override
    public String toString() {
        return String.format("{name: %s, score: %d}", name, score);
    }
}
