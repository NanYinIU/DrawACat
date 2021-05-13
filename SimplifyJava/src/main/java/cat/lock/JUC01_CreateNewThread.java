package cat.lock;

/**
 * @author nanyin
 * @class JUC01_CreateNewThread.java
 * @description 创建线程的方式
 * @create 17:44 2020-05-01
 */
public class JUC01_CreateNewThread {

    public static void main(String[] args) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("thread run");
            }
        };
        thread.start();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run");
            }
        };
        new Thread(run).start();

        Thread custom = new Thread(()-> {System.out.println("hello");});
        custom.start();
    }
}
