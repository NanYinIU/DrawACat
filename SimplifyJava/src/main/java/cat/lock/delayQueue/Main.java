package cat.lock.delayQueue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/6/20 20:08
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();

        // Add elements to the DelayQueue
        delayQueue.add(new DelayedElement("Element 1", 2000)); // Delay of 2000 milliseconds
        delayQueue.add(new DelayedElement("Element 2", 5000)); // Delay of 5000 milliseconds
        delayQueue.add(new DelayedElement("Element 3", 3000)); // Delay of 3000 milliseconds

        // ...

        // Take elements from the DelayQueue object
        while (!delayQueue.isEmpty()) {
            try {
                DelayedElement take = delayQueue.take();
                System.out.println(take.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDelay() throws InterruptedException {
        // given
        ExecutorService executor = Executors.newFixedThreadPool(2);

        DelayQueue<DelayedElement> queue = new DelayQueue<>();
        int numberOfElementsToProduce = 5;
        int delayOfEachProducedMessageMilliseconds = 500;
        DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
        DelayQueueProducer producer = new DelayQueueProducer(queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

        // when
        executor.submit(producer);
        executor.submit(consumer);

        // then
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();

//        assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
    }
}
