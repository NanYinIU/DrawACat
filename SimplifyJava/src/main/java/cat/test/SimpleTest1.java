package cat.test;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 简单测试
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-27
 */
public class SimpleTest1 {

    private static Random random = new Random();
    @Test
    public void simpleTest1_1(){
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append("《").append(s).append("》、");
        }
        if (list.size() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void simpleTest1_2(){
        long atime = 1607738400000L;
        long now = System.currentTimeMillis();
        System.out.println(atime <= now);
    }

    @Test
    public void testThrowException(){
        try{
            testThrowException01();
        }catch (ArithmeticException a){
            System.out.println("throw...");
        }

    }

    /**
     * 不必向上抛，但是必须捕获
     */
    public void testThrowException01() throws NullPointerException{

        int a = 1/0;
        // 后面不会打印
        System.out.println("test");
    }

}
