package cat.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.jvm.hotspot.types.basic.BasicTypeDataBase;
import sun.misc.GC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
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
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
       List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> collect = list.stream().filter(x -> x == 2).collect(Collectors.toList());
        collect.forEach(System.out::println);
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
        List<Integer> list = Arrays.asList(1,2,11,9,3,5,0);
        List<Integer> collect = list.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        collect.forEach(System.out::println);
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


    @Test
    public void test1_15(){
        String result = doPost("http://docker1-oms.hongrenshuo.tv/v1/radio/drama/time/add", "", "UTF-8",6000,6000);
        System.out.println("result:"+result);
    }

    public static String doPost(String url, String param, String contentType, Integer readTimeout, Integer connectTimeout){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(readTimeout);
            conn.setConnectTimeout(connectTimeout);

            if (param != null && !param.trim().equals(""))
            {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
            }
        }
        return result;
    }


    @Test
    public void test1_16(){
        List<Integer> a = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
        int BATCH_SIZE = 5;
        int count = a.size();
        int size = count / BATCH_SIZE;
        for (int i = 1; i <= size + 1; i++) {
            int start = (i - 1) * BATCH_SIZE;
            int end = Math.min((i * BATCH_SIZE), count);
            System.out.println(start + " && " + end);
            List<Integer> integers = a.subList(start, end);
            System.out.println("size:"+integers.size());
            integers.forEach(System.out::println);
        }

    }


    @Test
    public void test1_17(){
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        Byte[] bytes = new Byte[5*1024*1024/8];
        int length = bytes.length;
        System.out.println(length);
        threadLocal.set(bytes);
//        threadLocal.remove();
//        threadLocal = null;
        bytes = null;
//        System.gc();

        ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
        Byte[] bytes2 = new Byte[2*1024*1024/8];
        threadLocal2.set(bytes2);


//        new Thread(() -> {
//            ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
//            Byte[] bytes2 = new Byte[2*1024*1024/8];
//            threadLocal2.set(bytes2);
//            Thread t2 = Thread.currentThread();
//            System.out.println(t2.getName());
//        }).start();

    }

    @Test
    public void test1_18(){
        String content = "$XXX$hahahah$YYY$档案袋$ZZZ$nsnsnnsn";
        String z = StringUtils.replaceEach(content, new String[]{"$ZZZ$","$XXX$","$YYY$"}, new String[]{null, String.valueOf(300), "哈哈"});
        System.out.println(z);
    }

    @Test
    public void test1_19(){
        try {
            String cellphone = decrypt("nIgqOoMVGUOhsbEXR/GeKQ==");
            System.out.println(cellphone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String content) throws Exception {
        SecretKeySpec skey = new SecretKeySpec("c98be79a4347bc97".getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/Pkcs5Padding");
        IvParameterSpec iv = new IvParameterSpec("93x0ue23c2c9h8km".getBytes());
        cipher.init(2, skey, iv);
        byte[] encrypted = cipher.doFinal(Base64.decode(content));
        return new String(encrypted);
    }


}
