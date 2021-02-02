package cat.test;

import org.junit.Test;
import sun.misc.GC;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

    @Test
    public void test1_4(){
        Long date = 1609123830000l;
        String mdd = new SimpleDateFormat("yyyyMMdd").format(new Date(date));
        System.out.println(mdd);

    }

    @Test
    public void test1_5(){
        Double a  = 1.609862399059E12;
        System.out.println(a);
        Double b = null;
        if(b == null || b.equals(a)){
            System.out.println("ddd");
        }

        Double c = 1609123830000.10234;
        Double d = 1609123830000.10235;

        System.out.println(c > d);
        System.out.println(d == c);
        System.out.println(d);
        System.out.println(c);
        System.out.println(c.equals(d));
    }

    @Test
    public void test1_6(){

        Long a = 1609123830000L;
        Long b = 1609123830001L;
        BigDecimal b1 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal(b);
        Long sy = System.currentTimeMillis();
        BigDecimal b3 = new BigDecimal(sy);
        double c = a + (sy-b) / 1440;
        System.out.println(Math.floor(c));
        BigDecimal bd = new BigDecimal(c);
        System.out.println(bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
        NumberFormat nf = NumberFormat.getInstance();

//        System.out.println(b1.add(b3.divide(b2,5,BigDecimal.ROUND_HALF_UP)).toString());
        nf.setMaximumFractionDigits(5);//设置保留多少位小数

        nf.setGroupingUsed(false);//取消科学计数法
        String format = nf.format(c);

        System.out.println(format);
    }

    @Test
    public void test1_7(){

        long a1 = System.currentTimeMillis();

        Long a = 1609754743000L;
        Long b = 1609123830001L;

        long early = 1609689600000L;
        long create = 1609754743000L;
        long timeInMillis = 1609765543000L;
        System.out.println(create - early);
        System.out.println( (create - early) / 1440);
        System.out.println(timeInMillis - (create - early) / 1440);



    }
    private static final long day = 24 * 60 * 60 * 1000L;


    @Test
    public void test1_8(){

        long early = 1609689600000L;
        long create = 1609986441000L;
        long timeInMillis = 1609775999000L;

        long startTimeOfDay = getStartTimeOfDay(early);
        long endTimeOfDay = getStartTimeOfDay(create);
        System.out.println((double)(endTimeOfDay-startTimeOfDay)/day/10);

    }

    public static Long getStartTimeOfDay(Long dateTime){
        if(dateTime == null){
            dateTime = System.currentTimeMillis();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTimeInMillis();
    }

    public static Long betweenDays(Long start,Long end){
        long startTimeOfDay = getStartTimeOfDay(start);
        long endTimeOfDay =getStartTimeOfDay(end);
        return (endTimeOfDay-startTimeOfDay)/day;
    }

    @Test
    public void test1_9(){
        Long createTime = 1610017058931L; // 2021-01-07 18:45:50
        long early = getStartTimeOfDay(createTime);
        long between = betweenDays(early, System.currentTimeMillis());
        long timeInMillis = 1610126700000l;
        // 相隔天数的系数计算
        double mut = 1 - (double) between / 10;
        // 创建时间系数计算
        long createTimeMut = (createTime - early) / 1440;
        System.out.println(Double.valueOf(createTimeMut)); // 23544.0
        System.out.println(createTimeMut * mut); // 23779.44
//        System.out.println(timeInMillis - createTimeMut * mut); //23544
        // 相隔天数越大，创建时间越早，值越大
        System.out.println(Double.valueOf(timeInMillis - createTimeMut * mut).longValue()); //1610427563779
//        System.out.println(Double.valueOf(timeInMillis - ((createTime - early) / 2880) ).longValue());
    }

    @Test
    public void test1_10() {
        Long createTime = 1610165162000L; // 2021-01-08 18:26:02
        long early = getStartTimeOfDay(createTime);
        long between = betweenDays(early, System.currentTimeMillis());
        long timeInMillis = 1610126700000l;
        // 相隔天数的系数计算
        double mut = 1 - (double) between / 10;
        // 创建时间系数计算
        long createTimeMut = (createTime - early) / 1440;
        System.out.println(Double.valueOf(createTimeMut)); // 23544.0
        System.out.println(createTimeMut * mut); // 24217.86
        double x = createTimeMut * mut;
        // 相隔天数越大，创建时间越早，值越大
        System.out.println(Double.valueOf(timeInMillis - x).longValue()); //1610427564217
//        System.out.println(Double.valueOf(timeInMillis - ((createTime - early) / 2880) ).longValue());

    }

    //  2021-01-08 12:06:02 1610078762000  > 16101266 69749
    //  2021-01-08 15:26:02 1610090762000  > 16101266 61416
    //  2021-01-08 18:26:02 1610101562000  > 16101266 53916

    //  2021-01-09 12:06:02 1610165162000 > 16101266 66723
    //  2021-01-10 12:06:02 1610251562000 > 16101266 63698
    //  2021-01-11 12:06:02 1610337962000 > 16101266 60673
    //  2021-01-15 12:06:02 1610683562000 > 16101266 48573

    // 1610427516779
    // 1610427516958

    // 1610126676779
    // 1610126676958

    // 1610126676759

    // 1610126678890
    // 1610126676958

    // 1610427516691
    // 1610427516731

    //1610427516556
    //1610427516535

    public static final String YYYYMMDDHHMM = "yyyy-mm-dd HH:mm";

    public static void format(Long date,String format){

        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String s = sdf.format(date);
            Date date1 = new Date(s);
            SimpleDateFormat formatter2  = new SimpleDateFormat(YYYYMMDDHHMM);
            try {
                System.out.println(formatter2.parse(s).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date1);
        }

//        if(dateTime == null){
//            dateTime = System.currentTimeMillis();
//        }
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(dateTime);
//        cal.set(Calendar.HOUR_OF_DAY, 23);
//        cal.set(Calendar.MINUTE, 59);
//        cal.set(Calendar.SECOND, 59);
//        cal.set(Calendar.MILLISECOND, 59);
//        return cal.getTimeInMillis();
    }

    @Test
    public void test1_11(){
        Long a = -28800000L;
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date(a));
        System.out.println(yyyyMMdd);

        System.out.println(new Date(1000).toLocaleString());

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(60000));
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date time = cal.getTime();
        System.out.println(time.toLocaleString());
        System.out.println(cal.getTimeInMillis());
    }

    @Test
    public void test1_12(){
        Long createTime = 1610380513000L; // 2021-01-09 12:06:02
//        1610364493000L;
//        1610372353000L;
//        1610295913000L;
//        1610380513000L;
        LocalDateTime localDate = Instant.ofEpochMilli(createTime).atZone(ZoneOffset.ofHours(8)).toLocalDateTime().withSecond(0).withNano(0);
        System.out.println(localDate);
        long b = localDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(b);
        int minute = localDate.getMinute();
        int hour = localDate.getHour();
        if(minute < 30){
           minute = 0;
        }else {
           minute = 30;
        }
        LocalDateTime localDateTime = localDate.withHour(hour).withMinute(minute);
        System.out.println(localDateTime);
        long a = localDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(a);

    }

    @Test
    public void test1_13(){
        Long time = 1610623176106L;
        long mil = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()).toLocalDate().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println(mil);
    }

    @Test
    public void test1_14(){
        Object obj = new Object();
        System.gc();

        SoftReference softObj = new SoftReference(obj);

//        ReferenceQueue queue = new ReferenceQueue();
//        SoftReference softObj1 = new SoftReference(obj,queue);
//        obj = null;

        WeakReference<Object> weakObj = new WeakReference<Object>(obj);


        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Object> phantomObj = new PhantomReference<Object>(obj , queue);

        //去除强引用
        obj = null;
    }
}
