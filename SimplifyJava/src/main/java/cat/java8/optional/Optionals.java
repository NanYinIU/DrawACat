package cat.java8.optional;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.uxin.commons.common.User;
import com.uxin.commons.logutil.ThreadPoolExecutorTraceId;
import com.uxin.zb.room.service.model.RoomInfo;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * day4
 * Optional是可以整洁的防止空指针异常，Option就是一个容器类，里面的内容可以为null，也可以为 not-null,
 * 以前返回null值的都可以使用Optional进行替代。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-23
 */
public class Optionals {

    @Test
    public void testSimpleOptional(){
        Optional<String> simpleOptional = Optional.of("test");
        System.out.println(simpleOptional.get());
        System.out.println(simpleOptional.isPresent());

    }

    @Test
    public void test1(){
        Long uid = null;
        User user = new User();
        user.setUid(111L);
        if (user != null) {
            uid = user.getUid();
        }


        Optional<User> userOpt = Optional.ofNullable(user);
        Optional<Long> uidOpt = userOpt.map(User::getUid);

        System.out.println(uidOpt.orElse(0L));
    }


    private static final int UUID_BUSINESS_NUM = 4;
    private static long serverId = 0;
    private static long haid = 0;
    private static long[] seqidarray = new long[]{0, 0, 0, 0};
    private static long[] seconds = new long[]{0, 0, 0, 0};
    private static long getUuid(int id) {
        long uuid = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(+8));

        id = (id < 0 || id > UUID_BUSINESS_NUM - 1) ? 0 : id;
        if (seconds[id] != uuid) {
            seconds[id] = uuid;
            seqidarray[id] = ThreadLocalRandom.current().nextInt() & ((1 << 5) - 1); //seqid start from a random number between 0 and 63
        }

        uuid -= 1262275200L;
        System.out.println(uuid);
        uuid = uuid << 32;
        // 9 << 32  23
        System.out.println(uuid);
        uuid += serverId << 28;
        System.out.println(uuid);
        uuid += id << 26;
        System.out.println(uuid);
        ++seqidarray[id];
        seqidarray[id] = seqidarray[id] % (1 << 25);
        System.out.println(uuid);
        uuid += seqidarray[id] << 1; //seq num
        System.out.println(uuid);
        uuid += haid; //ha id
        System.out.println(uuid);
        return uuid;

    }

    @Test
    public void test1_1(){
        Integer amount = 100;

//        getUuid(0);

        Integer feeRatioAmount = new Double(Math.floor(amount *  20 * 1.0 / 100)).intValue();
        amount = amount - feeRatioAmount;
        System.out.println(amount);

        boolean right = isRight(100, 10);
        System.out.println(right);
    }

    private boolean isRight(int money, int count) {
        double avg = money / count;
        //小于最小金额
        if (avg < 10) {
            return false;
            //大于最大金额
        } else if (avg > 1000000) {
            return false;
        }
        return true;
    }
    private static final ThreadFactory statisticsThreadFactory =
            new ThreadFactoryBuilder().setNameFormat("roomServiceStatistics-%d").build();
    //任务量很大
    private static final ThreadPoolExecutor statisticsExecutor =
            new ThreadPoolExecutorTraceId(150, 200, 3600, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), statisticsThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());


    @Test
    public void test1_2() {
        // 这里模拟 portal 调用
        Future<Integer> future = statisticsExecutor.submit(() -> test_aync());
        try {
            future.get(600, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("parent occur: "+e);
        }
    }

    public Integer test_aync(){
        List<Integer> org = new ArrayList<>();
        org.add(1);
        org.add(2);
        org.add(3);
        org.add(4);
        org.add(5);
        org.add(6);

        List<Future<Integer>> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        // 调用耗时service
        org.forEach(x -> futureList.add(statisticsExecutor.submit(() -> plus_one(x))));
        futureList.forEach(future -> {
            try {
                future.get(300, TimeUnit.MILLISECONDS);
                long temp = System.currentTimeMillis() -start;
                System.out.println("after future get cost time:" + temp);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println("xxx:"+e);
                long temp2 = System.currentTimeMillis() -start;
                System.out.println("after future get cost time2:" + temp2);
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return 1;
    }

    public Integer plus_one(Integer integer){
        try {
            Thread.sleep(595);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return integer + 1;
    }



}
