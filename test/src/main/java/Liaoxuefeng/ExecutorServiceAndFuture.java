package Liaoxuefeng;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

public class ExecutorServiceAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<BigDecimal> future = es.submit(new Task("601857"));
        System.out.println("调运future时如果线程还没有返回，调用线程会被阻塞");
        System.out.println(future.get());
        es.shutdown();
    }
}


class Task implements Callable<BigDecimal> {

    public Task(String code) {

    }

    @Override
    public BigDecimal call() throws Exception {
        Thread.sleep(3000);
        double d = 5 + Math.random() * 20;
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN);
    }
}
