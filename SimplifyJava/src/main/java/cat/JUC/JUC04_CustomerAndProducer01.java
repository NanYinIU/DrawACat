package cat.JUC;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author nanyin
 * @class JUC04_CustomerAndProducer01.java
 * @description TODO
 * @create 13:59 2020-05-17
 */
public class JUC04_CustomerAndProducer01 {
    List<Integer> repostory = new LinkedList<>();
    private int size = 0;
    private int MAX = 15;

    public synchronized void add(Integer value) throws InterruptedException {
        while (size == MAX) {
            System.out.println(Thread.currentThread().getName() + " size:" + size + " block producer thread!!");
            this.wait();
        }
        repostory.add(value);
        size++;
        System.out.println("current thread "+Thread.currentThread().getName()+" increase size , current size is :" + size);
        this.notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        while (size <= 0) {
            System.out.println(Thread.currentThread().getName() + " size:" + size + " block customer thread!!");
            this.wait();
        }
        repostory.remove(0);
        size--;
        System.out.println("current thread "+Thread.currentThread().getName()+" increase size , current size is :" + size);
        this.notifyAll();
    }

    public static void main(String[] args) {
        JUC04_CustomerAndProducer01 c = new JUC04_CustomerAndProducer01();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        c.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c_" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    try {
                        c.add(new Random().nextInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "p_" + i).start();
        }

    }

}
