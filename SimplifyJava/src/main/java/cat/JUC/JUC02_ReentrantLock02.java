package cat.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nanyin
 * @class JUC02_ReentrantLock02.java
 * @description TODO
 * @create 20:51 2020-05-07
 */
public class JUC02_ReentrantLock02 {

    ReentrantLock lock = new ReentrantLock();

    void runSomething(){
        try {
            if(lock.tryLock(1, TimeUnit.SECONDS)){
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName());
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println("获取锁超时！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JUC02_ReentrantLock02 reentrantLock = new JUC02_ReentrantLock02();
        Thread t1 =  new Thread(){
            @Override
            public void run() {
                try {
                    reentrantLock.runSomething();
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    System.out.println(getName());
                }
            }
        };

        Thread t2 =  new Thread(){
            @Override
            public void run() {
                System.out.println("get lock..");
                reentrantLock.runSomething();
            }
        };
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
