package cat.lock.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: gaoguoxing
 * @date: 2023/7/6 16:50
 * @version: 1.0
 */
public class BalanceTest {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("balance-task-%d").build(), new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {
        BalanceTest balanceTest = new BalanceTest();

        balanceTest.execute();
    }

    private void execute() {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            BalanceWorker worker = new BalanceWorker(countDownLatch);
            threadPoolExecutor.execute(worker);
        }
        System.out.println("开始执行");
        // 执行完countdownlatch 后执行
        try {
            countDownLatch.await();
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
