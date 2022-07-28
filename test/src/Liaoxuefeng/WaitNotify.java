package Liaoxuefeng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();//任务队列
        List<Thread> ts = new ArrayList<>();//线程列表

        //创建5个线程读取任务
        for(int i=0;i<5;i++){
            Thread t = new ThisThread(i){
                //线程循环读取任务直到catch到InterruptedException
                public void run(){
                    while(true){
                        try{
                            String s= q.getTask();
                            System.out.println("----------\n线程id: " + id + "  任务名称: " + s + "  sleep:100ms\n----------\n");
                            //线程sleep 100ms
                            Thread.sleep(100);
                            System.out.println("###########线程id: " + id + "  sleep结束###########");

                        }catch (InterruptedException e){
                            System.out.println("线程id: " + id + "关闭");
                            return;
                        }
                    }
                }
            };
            //启动线程并添加到线程列表
            t.start();
            ts.add(t);
        }

        //创建1个线程添加10个task
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

        //启动add线程并让main线程等待其结束
        add.start();
        add.join();
        Thread.sleep(1000);

        //中断线程列表中所有线程
        // ps:因为所有的读取线程在10个任务读取完后一直处于wait状态并不会关闭需要手动关闭
        //在run()中catch到InterruptedException后会return run函数
        for(Thread t:ts){
            t.interrupt();
        }
    }
}

class TaskQueue{
    Queue<String> queue = new LinkedList<>();

    //每次添加完task,唤醒所有在wait的线程
    public synchronized void addTask(String task){
        queue.add(task);
        this.notifyAll();
    }

    //当任务队列queue为空的时候进入wait状态
    public synchronized String getTask() throws InterruptedException {
        while(queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }
}

class ThisThread extends Thread{
    public int id;
    ThisThread(int id){
        super();
        this.id=id;
    }
}
