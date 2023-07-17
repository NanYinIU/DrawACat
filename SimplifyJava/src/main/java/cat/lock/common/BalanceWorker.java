package cat.lock.common;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/7/6 16:46
 * @version: 1.0
 */
public class BalanceWorker implements Runnable {

    private CountDownLatch countDownLatch;

    public BalanceWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.printf("开始执行任务...当前线程id：%s%n", Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.printf("完成执行任务..当前线程id：%s%n", Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}
