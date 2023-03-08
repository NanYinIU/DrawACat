package cat.lock;

/**
 * @author nanyin
 * @class JUC04_StopOtherThread01.java
 * @description TODO
 * @create 14:57 2020-05-17
 */
public class JUC04_StopOtherThread01 {

    private int number = 0;

    public synchronized void intercept() {
        if (number != 5) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("print that message !!!");
        this.notify();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("intercept finished!!");
    }

    public synchronized void increase() {
        while (number <= 10) {
            if (number == 5) {
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println("current number size is :" + number);
        }
        this.notify();
    }

    public static void main(String[] args) {
        JUC04_StopOtherThread01 stopOtherThread01 = new JUC04_StopOtherThread01();
        new Thread(() -> {
            stopOtherThread01.intercept();
        }).start();

        new Thread(() -> {
            stopOtherThread01.increase();
        }).start();
    }

    public String huiwen(String s, int left, int right) {
        while (left > 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
        }
        return s.substring(left, right);
    }
}
