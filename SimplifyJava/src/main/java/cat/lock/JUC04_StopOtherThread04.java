package cat.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author nanyin
 * @class JUC04_StopOtherThread01.java
 * @description TODO
 * @create 14:57 2020-05-17
 */
public class JUC04_StopOtherThread04 {
    int number = 0;

    Thread t1 = null;
    Thread t2 = null;

    private void increase() {
        number++;
    }

    private int size() {
        return number;
    }

    public static void main(String[] args) {



        JUC04_StopOtherThread04 s = new JUC04_StopOtherThread04();


        s.t1 = new Thread(() -> {
            try {
                if (s.size() != 5) {
                  LockSupport.park();
                  LockSupport.unpark(s.t2);
                }
                System.out.println("print that message !!!");

                System.out.println("all finished!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        });

        s.t2 = new Thread(() -> {
            try {
                while (s.size() < 10) {
                    if (s.size() == 5) {
                        LockSupport.unpark(s.t1);
                        LockSupport.park();
                    }
                    s.increase();
                    System.out.println("current number size is :" + s.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }

        });

        s.t1.start();
        s.t2.start();
    }
}
