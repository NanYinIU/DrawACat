package cat.test;

import com.uxin.commons.redis.RedissonManager;
import com.uxin.zb.biz.commons.model.ThirdChannelType;
import org.junit.Test;
import org.redisson.api.RLock;

import java.util.Arrays;
import java.util.concurrent.DelayQueue;
import java.util.stream.Stream;


/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2022-01-05
 */
public class caseTest {

    private static boolean caseTest(ThirdChannelType thirdChannelType){
        switch (thirdChannelType) {
            case HONGDOU:
                System.out.println(1);
            case MANBO:
                System.out.println(2);
            case KLIVE:
                System.out.println(3);
            default:
                System.out.println("default");
        }
        return true;
    }

    public static void main(String[] args) {
        caseTest(ThirdChannelType.TALKER);
    }

    @Test
    public void testDelayQueue(){
        Long now = System.currentTimeMillis();
        Long produceTime = 1641340800000L;
        int giftPushMsgDistinctMinutes = 5;
        System.out.println(now - produceTime > giftPushMsgDistinctMinutes * 60 * 1000);

        String name = String.format("hello {world} %s", "world");
        System.out.println(name);
    }

    @Test
    public void testRedissonLock(){
        RedissonManager  manager = new RedissonManager();

        manager.setAddress("60.205.59.6:6337");
        manager.setPassword("uxin001");
        manager.setLockWatchdogTimeout(10000L);
        manager.init();
        RLock rLock = null;
        try{
            rLock = manager.getLock("test_001");
            boolean result = rLock.tryLock();
            System.out.println(rLock.remainTimeToLive());

            Thread.sleep(1000);
            System.out.println(rLock.remainTimeToLive());

            Thread.sleep(1000);
            System.out.println(rLock.remainTimeToLive());

            Thread.sleep(1000);
            System.out.println(rLock.remainTimeToLive());
            // 执行了一次续期，从新设置过期时间 10s

            Thread.sleep(2000);
            System.out.println(rLock.remainTimeToLive());

            Thread.sleep(2000);
            System.out.println(rLock.remainTimeToLive());

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(rLock != null) {
                rLock.unlock();
            }
        }
    }

    @Test
    public void test(){
        String[] arr = new String[]{"2","3","4"};
        try{
            Stream.of(arr).mapToInt(Integer::parseInt).reduce((a,b) -> {
                if(a > b){
                    throw new RuntimeException("error");
                }
                return b;
            });
        }catch (NumberFormatException e){
            System.out.println("error"+e);
        }

    }

}
