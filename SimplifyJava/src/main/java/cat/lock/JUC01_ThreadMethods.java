package cat.lock;

/**
 * @author nanyin
 * @class JUC01_ThreadMethods.java
 * @description TODO
 * @create 18:38 2020-05-01
 */
public class JUC01_ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if(i == 2){
                    Thread.yield();
                }
                System.out.println("t run " + i + "time ");
            }
        });
        t.setName("custom-thread-1");

        Thread m = new Thread(()->{
            try {
                // 等待t线程结束
                System.out.println("before join ");
                t.join();
                System.out.println("after join ");
                for (int i = 0; i < 10; i++) {
                    System.out.println("m run " + i + "time ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        m.setName("custom-thread-2");

//        t.start();
//        Thread.sleep(3000);
//        m.start();
        m.start();
        t.start();

    }
}
