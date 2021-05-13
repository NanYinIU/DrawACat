package cat.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author nanyin
 * @class JUC04_StopOtherThread01.java
 * @description TODO
 * @create 14:57 2020-05-17
 */
public class JUC04_StopOtherThread03 {
    int number = 0;

    private void increase() {
        number++;
    }

    private int size() {
        return number;
    }

    public static void main(String[] args) {
        JUC04_StopOtherThread03 s = new JUC04_StopOtherThread03();

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(() -> {
            try {
                if (s.size() != 5) {
                    latch1.await();
                    latch2.countDown();
                }
                System.out.println("print that message !!!");

                System.out.println("all finished!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }).start();

        new Thread(() -> {
            try {
                while (s.size() < 10) {
                    if (s.size() == 5) {
                        latch1.countDown();

                        latch2.await();
                    }
                    s.increase();
                    System.out.println("current number size is :" + s.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }

        }).start();
    }
}
