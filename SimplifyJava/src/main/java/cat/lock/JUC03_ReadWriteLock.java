package cat.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author nanyin
 * @class JUC03_ReadWriteLock.java
 * @description TODO
 * @create 19:06 2020-05-10
 */
public class JUC03_ReadWriteLock {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Integer msg = 0;

    public /*synchronized*/ void read(){
        Lock read = readWriteLock.readLock();
        try {
            read.lock();
            System.out.println("reading msg is "+msg);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.unlock();
        }
    }

    public /*synchronized*/ void write(){
        Lock write = readWriteLock.writeLock();
        try{
            write.lock();
            msg = new Random().nextInt();
            System.out.println("write msg .."+ msg);
            Thread.sleep(1000);
        }catch (Exception e){

        }finally {
            write.unlock();
        }
    }

    public static void main(String[] args) {
        JUC03_ReadWriteLock juc03_readWriteLock = new JUC03_ReadWriteLock();

        for (int i = 0; i < 9; i++) {
            Thread readThread = new Thread(() -> {
                juc03_readWriteLock.read();
            });
            readThread.start();
        }

        for (int i = 0; i < 2; i++) {
            Thread writeThread = new Thread(()->{
                juc03_readWriteLock.write();
            });
            writeThread.start();
        }
    }
}
