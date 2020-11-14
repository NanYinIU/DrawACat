package cat.JUC;

/**
 * @author nanyin
 * @class test1.java
 * @description TODO
 * @create 14:56 2020-05-18
 */
public class test1 {
    public static void main(String[] args) {
        test1 test1 = new test1();

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                synchronized (test1){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" synchronized...");
                }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" hello");

            }).start();
        }
    }

    public void symethod(){

    }
}
