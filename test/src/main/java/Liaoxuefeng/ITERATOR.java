package Liaoxuefeng;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ITERATOR {
    public static void main(String[] args) {
        ReverseList<String> list = new ReverseList<>();
        list.add("orange");
        list.add("pink");
        list.add("white");
        for (String s : list) {
            System.out.println(s);
        }

    }
}

class ReverseList<T> implements Iterable<T> {
    private final List<T> list = new LinkedList<>();

    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
    }

    class ReverseIterator implements Iterator<T> {
        int index;

        ReverseIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            index--;
            return list.get(index);
        }

        @Override
        public void remove() {
            list.remove(index);
        }
    }


}