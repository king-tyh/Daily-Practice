package Liaoxuefeng;

import java.util.Deque;
import java.util.LinkedList;

public class STACK {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("!");
        stack.push("test");
        stack.push("a ");
        stack.push("is ");
        stack.push("This ");

        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.peek());
        System.out.print(stack.peek());
        System.out.print(stack.pop());
    }
}

class MyStack<T> {
    private final Deque<T> deque = new LinkedList<>();

    public T pop(){
        return deque.removeFirst();
    }

    public void push(T element){
        deque.addFirst(element);
    }

    public T peek(){
        return deque.peekFirst();
    }

}
