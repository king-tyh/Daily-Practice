package Liaoxuefeng;

import java.util.concurrent.locks.StampedLock;

public class STAMPEDLOCK {
    public static void main(String[] args) {
        Point point = new Point();
    }
}

class Point {
    private final StampedLock lock = new StampedLock();
    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();//获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);//释放写锁
        }

    }

    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = 0;
        double currentY = 0;
        //获取一个乐观读锁，可以多读，读时也可以写
        if (!lock.validate(stamp)) {//检查乐观锁读锁后有没有其他
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);//释放读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}
