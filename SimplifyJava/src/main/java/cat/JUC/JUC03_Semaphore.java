package cat.JUC;

import java.util.concurrent.Semaphore;

/**
 * @author nanyin
 * @class JUC03_Semaphore.java
 * @description TODO
 * @create 20:05 2020-05-10
 */
public class JUC03_Semaphore {

    Semaphore semaphore = new Semaphore(1);

    Thread t1 = new Thread(() -> {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        System.out.println("t1.....end");
    });

    Thread t2 = new Thread(() -> {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        System.out.println("t2.....end");
    });

    Thread t3 = new Thread(() -> {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        System.out.println("t3.....end");
    });

    public void run(){
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        JUC03_Semaphore juc03_semaphore = new JUC03_Semaphore();
        juc03_semaphore.run();
    }
}
