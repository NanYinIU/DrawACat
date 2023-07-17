package cat.lock.delayQueue;

import lombok.Data;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/6/20 21:26
 * @version: 1.0
 */

@Data
public class DelayQueueProducer implements Runnable {

    private DelayQueue<DelayedElement> queue;
    private Integer numberOfElementsToProduce;
    private Integer delayOfEachProducedMessageMilliseconds;

    // standard constructor

    public DelayQueueProducer(DelayQueue<DelayedElement> queue, Integer numberOfElementsToProduce, Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.numberOfElementsToProduce = numberOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToProduce; i++) {
            DelayedElement object
                    = new DelayedElement(
                    UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds - i);
            System.out.println("Put object: " + object);
            queue.put(object);
//                Thread.sleep(500);
        }
    }
}