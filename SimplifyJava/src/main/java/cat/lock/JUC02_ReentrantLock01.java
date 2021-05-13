package cat.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nanyin
 * @class JUC02_ReentrantLock01.java
 * @description TODO
 * @create 20:51 2020-05-07
 */
public class JUC02_ReentrantLock01 {

    ReentrantLock lock = new ReentrantLock();

    class Muit implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":"+ i);
            }
        }
    }

    void runSomething(){
        lock.lock();
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":"+ i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        JUC02_ReentrantLock01 reentrantLock = new JUC02_ReentrantLock01();
        Thread t1 = new Thread(()-> {reentrantLock.runSomething();});
        Thread t2 = new Thread(()-> {reentrantLock.runSomething();});
        t1.start();
        t2.start();
    }
}
