package cat.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author nanyin
 * @class JUC02_CyclicBarrier.java
 * @description TODO
 * @create 21:45 2020-05-07
 */
public class JUC02_CyclicBarrier {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println("发车。。。带走");
    });

    private void doSomething(){
        Thread t1 = new Thread(()-> {
            try {
                System.out.println("t1 begin");
                cyclicBarrier.await();
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()-> {
            try {

                System.out.println("t2 begin");
                cyclicBarrier.await();
                System.out.println("t2 end");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()-> {
            try {

                System.out.println("t3 begin");
                cyclicBarrier.await();
                System.out.println("t3 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(()-> {
            try {
                System.out.println("t4 begin");
                cyclicBarrier.await();
                System.out.println("t4 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        try{
            t1.start();
            Thread.sleep(1000);
            t2.start();
            Thread.sleep(1000);
            t3.start();
            Thread.sleep(1000);
            t4.start();
        }catch (Exception e){

        }

    }

    public static void main(String[] args) {
        JUC02_CyclicBarrier cyclicBarrier = new JUC02_CyclicBarrier();
        cyclicBarrier.doSomething();
    }
}
