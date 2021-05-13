package cat.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author nanyin
 * @class JUC02_CountDownLatch.java
 * @description TODO
 * @create 21:03 2020-05-07
 */
public class JUC02_CountDownLatch {

    private void useCountDownLatch(){
        Thread[] threads = new Thread[10];
        // set countDownLatch size
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            threads[i] =  new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(getName());
                        countDownLatch.countDown();
                    }
                }
            };
            threads[i].setName("thread-"+i);
            // execute Thread run then countDown..
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        System.out.println("Task Start!");

        try{
            // causes current thread wait until the countDownLatch down to zero
            // effect all thread finish run()
            countDownLatch.await();
        }catch (Exception e){

        }

        System.out.println("All Task is Done!");
    }

    public static void main(String[] args) {
        JUC02_CountDownLatch countDownLatch = new JUC02_CountDownLatch();
        countDownLatch.useCountDownLatch();
    }

}
