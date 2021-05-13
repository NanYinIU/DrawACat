package cat.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nanyin
 * @class JUC04_StopOtherThread01.java
 * @description TODO
 * @create 14:57 2020-05-17
 */
public class JUC04_StopOtherThread02 {
    int number = 0;

    private void increase() {
        number++;
    }

    private int size() {
        return number;
    }

    public static void main(String[] args) {
        JUC04_StopOtherThread02 s = new JUC04_StopOtherThread02();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                if (s.size() != 5) {
                    condition.await();
                }
                System.out.println("print that message !!!");
                condition.signal();

                condition.await();
                System.out.println("all finished!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (s.size() < 10) {
                    if (s.size() == 5) {
                        condition.signal();
                        condition.await();
                    }
                    s.increase();
                    System.out.println("current number size is :" + s.size());
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
    }
}
