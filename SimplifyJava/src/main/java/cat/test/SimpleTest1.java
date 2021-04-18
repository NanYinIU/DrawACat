package cat.test;

import org.junit.Test;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
            double x = 11.2;
            System.out.println(x);
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

        ThreadLocal<String> tl = new ThreadLocal<>();

        tl.set("ff");
    }

    @Test
    public void test1_2(){
        Long dateTime = 1608627902740L;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MILLISECOND,59);
        System.out.println(cal.getTime());
    }

    @Test
    public void test1_3(){

        List<Node> a1 = new ArrayList<>();
        Node n1 = new Node();n1.setLength(1609430400000l);n1.setName("n1");
        Node n2 = new Node();n2.setLength(1609171200000l);n2.setName("n2");
        Node n3 = new Node();n3.setLength(1609344000000l);n3.setName("n3");
        Node n4 = new Node();n4.setLength(12l);n4.setName("n4");
        Node n5 = new Node();n5.setLength(8l);n5.setName("n5");
        a1.add(n1);
        a1.add(n2);
        a1.add(n3);
        a1.add(n4);
        a1.add(n5);
        Long a = 1609430400000l;
        Long b = 1609171200000l;
        Long c = 1609171200000l;
        int compare = Long.compare(c, a);
        System.out.println(compare);

        Map<Long, List<Node>> collect = a1.stream().collect(Collectors.groupingBy(Node::getLength));
        System.out.println(collect);

        TreeMap<Long, List<Node>> treeMap = new TreeMap<>((o1, o2) -> o1 > o2 ? 1 : -1);
        collect.forEach(treeMap::put);
//        treeMap.putAll(collect);
        System.out.println(treeMap);
    }

    private final static long ONE_DAY_MILLISECOND = 24 * 60 * (60-1) * 1000L;

    @Test
    public void test1_4(){
        Long date = 1612022399000L;
        Long node = 1611936000000L;
        long tb = ONE_DAY_MILLISECOND;
        System.out.println(tb);
        Long date2 = node + tb;
        System.out.println(24 * 60 * 60 * 1000 - 1000);
//        Long date3 = node + 86399000;
        System.out.println(date2);
//        System.out.println(date3);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(node);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(date2);
        cal2.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal2.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal2.set(Calendar.DATE, cal.get(Calendar.DATE));
        long timeInMillis = cal2.getTimeInMillis();
        System.out.println(timeInMillis);

    }

    @Test
    public void test1_5(){
        Long node = 1611936000000L;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(node);
        ZoneId zoneId = java.time.ZoneId.systemDefault();
        long l = LocalDateTime
                .ofInstant(Instant.ofEpochSecond(node), ZoneOffset.ofHours(8))
//                .withHour(23).withMinute(59).withSecond(59).withNano(59)
                .toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long l1 = LocalDate.now().atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
//        long l = LocalDate.ofEpochDay(node).atTime(23, 59, 59).atZone(zoneId).toInstant().toEpochMilli();
        System.out.println(l);
        System.out.println(l1);
//        System.out.println(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).atTime(23, 59, 59).atZone(zoneId).toInstant().toEpochMilli());

        Date date = new Date(node);
        Instant instant = date.toInstant();
        long l3 = instant.atZone(zoneId).toLocalDate().atTime(23,59,59).atZone(zoneId).toInstant().toEpochMilli();
        System.out.println(l3);
    }

    @Test
    public void test1_11(){
        List<Integer> list = Arrays.asList(1,2,11,9,3,5,0);
        List<Integer> collect = list.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

}
