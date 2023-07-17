package cat.lock.delayQueue;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/6/20 21:27
 * @version: 1.0
 */
@Data
public class DelayQueueConsumer implements Runnable {
    private DelayQueue<DelayedElement> queue;
    private Integer numberOfElementsToTake;
    public AtomicInteger numberOfConsumedElements = new AtomicInteger();

    // standard constructors

    public DelayQueueConsumer(DelayQueue<DelayedElement> queue, Integer numberOfElementsToTake, AtomicInteger numberOfConsumedElements) {
        this.queue = queue;
        this.numberOfElementsToTake = numberOfElementsToTake;
        this.numberOfConsumedElements = numberOfConsumedElements;
    }

    public DelayQueueConsumer(DelayQueue<DelayedElement> queue, Integer numberOfElementsToTake) {
        this.queue = queue;
        this.numberOfElementsToTake = numberOfElementsToTake;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToTake; i++) {
            System.out.println("Consumer waiting to take element...");
            try {
                DelayedElement object = queue.take();
                numberOfConsumedElements.incrementAndGet();
                System.out.println("Consumer take: " + object);
            } catch (InterruptedException e) {
                System.out.println("Consumer was interrupted while waiting to take element from queue");
                e.printStackTrace();
            }
        }
    }
}
