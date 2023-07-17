package cat.lock.delayQueue;

import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/6/20 20:05
 * @version: 1.0
 */
public class DelayedElement implements Delayed {

    private String data;
    private long expirationTime;

    public DelayedElement(String data, long expirationTime) {
        this.data = data;
        this.expirationTime = expirationTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remainingTime = System.currentTimeMillis() + expirationTime;
//        return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
        int number = 5 * new Random().nextInt();
        System.out.println("number: " + number);
        return number;
    }

    @Override
    public int compareTo(Delayed other) {
        long diff = this.getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(diff, 0);
    }

    public String getData() {
        return data;
    }
}
