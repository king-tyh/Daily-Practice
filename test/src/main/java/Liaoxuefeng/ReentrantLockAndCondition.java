package Liaoxuefeng;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndCondition {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTaskQueue q = new ReentrantLockTaskQueue();
        List<Thread> ts = new LinkedList<>();
        //创建五个线程读取任务
        for(int i=0;i<5;i++){
            Thread t = new Thread(){
                public void run(){
                    while(true){
                       try{
                            String s = q.getTask();
                            System.out.println("getTask: " + s);
                            Thread.sleep(100);
                        }
                       catch (InterruptedException e){
                           System.out.println("线程关闭");
                           return;
                       }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        //创建新线程给人物列表添加10个任务
        Thread add = new Thread(){
            public void run(){
                //每次添加一个任务
                for(int i=0;i<10;i++){
                    String s = "t-" + Math.random();
                    System.out.println("add task "+s);
                    q.addTask(s);

                }
            }
        };
        add.start();
        add.join();

        //关闭五个进程，进程run收到InterruptException
        for(Thread t:ts)
            t.interrupt();

    }
}


//使用ReentrantLock和Condition的任务队列
class ReentrantLockTaskQueue{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<String> taskQueue = new LinkedList<>();

    //添加任务 添加一条任务后唤醒所有正在等待lock的线程
    public void addTask(String task){
        lock.lock();
        try{
            taskQueue.add(task);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //获取任务 当列表为空时进入wait状态等待被唤醒
    public String getTask() throws InterruptedException {
        lock.lock();
        try{
            while(taskQueue.isEmpty()) {
                condition.await();
            }
        }catch (InterruptedException e){
            System.out.println("getTask InterrupterException");
        }finally {
            lock.unlock();
        }

        return taskQueue.remove();
    }
}
