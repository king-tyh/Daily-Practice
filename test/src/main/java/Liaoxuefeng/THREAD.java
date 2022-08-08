package Liaoxuefeng;

public class THREAD {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        t.join();
        System.out.println("end");
    }
}

class MyThread extends Thread{
    public void run(){
        Thread hello = new HelloThread();
        //启动hello线程
        hello.start();
        try{
            hello.join();
        }catch (InterruptedException e){
            System.out.println("MyThread catch InterruptedException!");
        }
        hello.interrupt();
    }
}

class HelloThread extends Thread{
    public void run(){
        int n=0;
        while (!isInterrupted()){
            n++;
            System.out.println(n + " hello!");
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("HelloThread catch InterruptedException!");
                break;
            }
        }
    }
}
