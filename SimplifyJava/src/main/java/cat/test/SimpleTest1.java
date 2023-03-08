package cat.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.uxin.commons.api.BusinessException;
import com.uxin.commons.crypto.AESUtil;
import com.uxin.commons.json.JsonConverter;
import com.uxin.commons.logutil.StatLog;
import com.uxin.commons.util.CollectionUtils;
import com.uxin.commons.util.MD5Util;
import com.uxin.zb.biz.commons.model.PushCustomMessageType;
import com.uxin.zb.biz.commons.model.PushType;
import com.uxin.zb.room.service.model.RoomInfo;
import com.uxin.zb.user.service.model.UserDeviceInfo;
import com.uxin.zb.user.service.model.UserInfo;
import com.uxin.zb.web.portal.user.vo.UserResp;
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
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.GC;

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
  public void simpleTest1_1() {
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
  public void simpleTest1_2() {
    long atime = 1607738400000L;
    long now = System.currentTimeMillis();
    System.out.println(atime <= now);
  }

  @Test
  public void testThrowException() {
    try {
      double x = 11.2;
      System.out.println("abc");
      System.out.println(x);
      testThrowException01();
    } catch (ArithmeticException a) {
      System.out.println("throw...");
    }
  }

  /**
   * 不必向上抛，但是必须捕获
   */
  public void testThrowException01() throws NullPointerException {

    int a = 1 / 0;
    // 后面不会打印
    System.out.println("test");

    ThreadLocal<String> tl = new ThreadLocal<>();

    tl.set("ff");
  }

  @Test
  public void test1_2() {
    Long dateTime = 1608627902740L;
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(dateTime);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 59);
    System.out.println(cal.getTime());
  }

  @Test
  public void test1_3() {

    List<Node> a1 = new ArrayList<>();
    Node n1 = new Node();
    n1.setLength(1609430400000l);
    n1.setName("n1");
    Node n2 = new Node();
    n2.setLength(1609171200000l);
    n2.setName("n2");
    Node n3 = new Node();
    n3.setLength(1609344000000l);
    n3.setName("n3");
    Node n4 = new Node();
    n4.setLength(12l);
    n4.setName("n4");
    Node n5 = new Node();
    n5.setLength(8l);
    n5.setName("n5");
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
    // treeMap.putAll(collect);
    System.out.println(treeMap);
  }

  private final static long ONE_DAY_MILLISECOND = 24 * 60 * (60 - 1) * 1000L;

  @Test
  public void test1_4() {
    Long date = 1612022399000L;
    Long node = 1611936000000L;
    long tb = ONE_DAY_MILLISECOND;
    System.out.println(tb);
    Long date2 = node + tb;
    System.out.println(24 * 60 * 60 * 1000 - 1000);
    // Long date3 = node + 86399000;
    System.out.println(date2);
    // System.out.println(date3);
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
  public void test1_5() {
    Long node = 1611936000000L;
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(node);
    ZoneId zoneId = java.time.ZoneId.systemDefault();
    long l = LocalDateTime.ofInstant(Instant.ofEpochSecond(node), ZoneOffset.ofHours(8))
        // .withHour(23).withMinute(59).withSecond(59).withNano(59)
        .toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    long l1 = LocalDate.now().atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    // long l = LocalDate.ofEpochDay(node).atTime(23, 59,
    // 59).atZone(zoneId).toInstant().toEpochMilli();
    System.out.println(l);
    System.out.println(l1);
    // System.out.println(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).atTime(23,
    // 59, 59).atZone(zoneId).toInstant().toEpochMilli());

    Date date = new Date(node);
    Instant instant = date.toInstant();
    long l3 = instant.atZone(zoneId).toLocalDate().atTime(23, 59, 59).atZone(zoneId).toInstant().toEpochMilli();
    System.out.println(l3);
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> collect = list.stream().filter(x -> x == 2).collect(Collectors.toList());
    collect.forEach(System.out::println);
  }

  @Test
  public void test1_6() {

    Long a = 1609123830000L;
    Long b = 1609123830001L;
    BigDecimal b1 = new BigDecimal(a);
    BigDecimal b2 = new BigDecimal(b);
    Long sy = System.currentTimeMillis();
    BigDecimal b3 = new BigDecimal(sy);
    double c = a + (sy - b) / 1440;
    System.out.println(Math.floor(c));
    BigDecimal bd = new BigDecimal(c);
    System.out.println(bd.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
    NumberFormat nf = NumberFormat.getInstance();

    // System.out.println(b1.add(b3.divide(b2,5,BigDecimal.ROUND_HALF_UP)).toString());
    nf.setMaximumFractionDigits(5); // 设置保留多少位小数

    nf.setGroupingUsed(false); // 取消科学计数法
    String format = nf.format(c);

    System.out.println(format);
  }

  @Test
  public void test1_7() {

    long a1 = System.currentTimeMillis();

    Long a = 1609754743000L;
    Long b = 1609123830001L;

    long early = 1609689600000L;
    long create = 1609754743000L;
    long timeInMillis = 1609765543000L;
    System.out.println(create - early);
    System.out.println((create - early) / 1440);
    System.out.println(timeInMillis - (create - early) / 1440);
  }

  private static final long day = 24 * 60 * 60 * 1000L;

  @Test
  public void test1_8() {

    long early = 1609689600000L;
    long create = 1609986441000L;
    long timeInMillis = 1609775999000L;

    long startTimeOfDay = getStartTimeOfDay(early);
    long endTimeOfDay = getStartTimeOfDay(create);
    System.out.println((double) (endTimeOfDay - startTimeOfDay) / day / 10);
  }

  public static Long getStartTimeOfDay(Long dateTime) {
    if (dateTime == null) {
      dateTime = System.currentTimeMillis();
    }
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(dateTime);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTimeInMillis();
  }

  public static Long betweenDays(Long start, Long end) {
    long startTimeOfDay = getStartTimeOfDay(start);
    long endTimeOfDay = getStartTimeOfDay(end);
    return (endTimeOfDay - startTimeOfDay) / day;
  }

  @Test
  public void test1_9() {
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
    // System.out.println(timeInMillis - createTimeMut * mut); //23544
    // 相隔天数越大，创建时间越早，值越大
    System.out.println(Double.valueOf(timeInMillis - createTimeMut * mut).longValue()); // 1610427563779
    // System.out.println(Double.valueOf(timeInMillis - ((createTime - early) /
    // 2880) ).longValue());
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
    System.out.println(Double.valueOf(timeInMillis - x).longValue()); // 1610427564217
    // System.out.println(Double.valueOf(timeInMillis - ((createTime - early) /
    // 2880) ).longValue());
  }

  public static final String YYYYMMDDHHMM = "yyyy-mm-dd HH:mm";

  public static void format(Long date, String format) {

    if (date != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      String s = sdf.format(date);
      Date date1 = new Date(s);
      SimpleDateFormat formatter2 = new SimpleDateFormat(YYYYMMDDHHMM);
      try {
        System.out.println(formatter2.parse(s).getTime());
      } catch (ParseException e) {
        e.printStackTrace();
      }
      System.out.println(date1);
    }

    // if(dateTime == null){
    // dateTime = System.currentTimeMillis();
    // }
    // Calendar cal = Calendar.getInstance();
    // cal.setTimeInMillis(dateTime);
    // cal.set(Calendar.HOUR_OF_DAY, 23);
    // cal.set(Calendar.MINUTE, 59);
    // cal.set(Calendar.SECOND, 59);
    // cal.set(Calendar.MILLISECOND, 59);
    // return cal.getTimeInMillis();
  }

  @Test
  public void test1_11() {
    List<Integer> list = Arrays.asList(1, 2, 11, 9, 3, 5, 0);
    List<Integer> collect = list.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
    collect.forEach(System.out::println);
    Long a = -28800000L;
    String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date(a));
    System.out.println(yyyyMMdd);

    System.out.println(new Date(1000).toLocaleString());

    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date(60000));
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    Date time = cal.getTime();
    System.out.println(time.toLocaleString());
    System.out.println(cal.getTimeInMillis());
  }

  @Test
  public void test1_12() {
    Long createTime = 1610380513000L; // 2021-01-09 12:06:02
    // 1610364493000L;
    // 1610372353000L;
    // 1610295913000L;
    // 1610380513000L;
    LocalDateTime localDate = Instant.ofEpochMilli(createTime).atZone(ZoneOffset.ofHours(8)).toLocalDateTime()
        .withSecond(0).withNano(0);
    System.out.println(localDate);
    long b = localDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    System.out.println(b);
    int minute = localDate.getMinute();
    int hour = localDate.getHour();
    if (minute < 30) {
      minute = 0;
    } else {
      minute = 30;
    }
    LocalDateTime localDateTime = localDate.withHour(hour).withMinute(minute);
    System.out.println(localDateTime);
    long a = localDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();

    System.out.println(a);
  }

  @Test
  public void test1_13() {
    Long time = 1610623176106L;
    long mil = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()).toLocalDate()
        .atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    System.out.println(mil);
  }

  @Test
  public void test1_14() {
    Object obj = new Object();
    System.gc();

    SoftReference softObj = new SoftReference(obj);

    // ReferenceQueue queue = new ReferenceQueue();
    // SoftReference softObj1 = new SoftReference(obj,queue);
    // obj = null;

    WeakReference<Object> weakObj = new WeakReference<Object>(obj);

    ReferenceQueue queue = new ReferenceQueue();
    PhantomReference<Object> phantomObj = new PhantomReference<Object>(obj, queue);

    // 去除强引用
    obj = null;
  }

  @Test
  public void test1_15() {
    String result = doPost("http://docker1-oms.hongrenshuo.tv/v1/radio/drama/time/add", "", "UTF-8", 6000, 6000);
    System.out.println("result:" + result);
  }

  public static String doPost(String url, String param, String contentType, Integer readTimeout,
      Integer connectTimeout) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
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

      if (param != null && !param.trim().equals("")) {
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
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
    }
    // 使用finally块来关闭输出流、输入流
    finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
      }
    }
    return result;
  }

  @Test
  public void test1_16() {
    List<Integer> a = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
    int BATCH_SIZE = 5;
    int count = a.size();
    int size = count / BATCH_SIZE;
    for (int i = 1; i <= size + 1; i++) {
      int start = (i - 1) * BATCH_SIZE;
      int end = Math.min((i * BATCH_SIZE), count);
      System.out.println(start + " && " + end);
      List<Integer> integers = a.subList(start, end);
      System.out.println("size:" + integers.size());
      integers.forEach(System.out::println);
    }
  }

  @Test
  public void test1_17() {
    ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    Byte[] bytes = new Byte[5 * 1024 * 1024 / 8];
    int length = bytes.length;
    System.out.println(length);
    threadLocal.set(bytes);
    // threadLocal.remove();
    // threadLocal = null;
    bytes = null;
    // System.gc();

    ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
    Byte[] bytes2 = new Byte[2 * 1024 * 1024 / 8];
    threadLocal2.set(bytes2);

    // new Thread(() -> {
    // ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();
    // Byte[] bytes2 = new Byte[2*1024*1024/8];
    // threadLocal2.set(bytes2);
    // Thread t2 = Thread.currentThread();
    // System.out.println(t2.getName());
    // }).start();
  }

  @Test
  public void test1_18() {
    String content = "$XXX$hahahah$YYY$档案袋$ZZZ$nsnsnnsn";
    String z = StringUtils.replaceEach(content, new String[] { "$ZZZ$", "$XXX$", "$YYY$" },
        new String[] { null, String.valueOf(300), "哈哈" });
    System.out.println(z);
  }

  /**
   * 手机号的加密解密
   */
  @Test
  public void test1_19() {
    try {
      String cellphone = decrypt("OZXvII4VTCA/msnhBXW6Dw==");
      System.out.println(cellphone);
      String cs = AESUtil.encrypt("18926575292");
      // String cs = "hSoECNpg168q3yM3tSAmbQ==";
      // String decrypt = AESUtil.decrypt(cs);
      // System.out.println(decrypt);
      System.out.println(cs);
      // String password = "b9e5869984098357414b142692e0157b";
      // String calculateMD5 =
      // MD5Util.calculateMD5(AESUtil.decrypt(password).getBytes());
      // System.out.println(calculateMD5);
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

  @Test
  public void test1_20() {
    DateTime dateTime = new DateTime(new Date());
    System.out.println(new Date());
    dateTime = dateTime.plusDays(2);
    Date date = dateTime.millisOfDay().withMaximumValue().toDate();
    System.out.println(date);
    System.out.println(date.getTime());
  }

  @Test
  public void test1_21() {
    Long a1 = 1201l;
    Long a2 = 356l;
    Long prop = 60l;
    System.out.println(prop * 1.0 / 100);
    Double result = (a1 - a2) * (prop * 1.0 / 100);
    Long ceil = new Double(Math.ceil(result)).longValue();
    System.out.println(ceil);
    System.out.println(result);
  }

  @Test
  public void test1_22() {
    String propStr = "{\"propIds\":[{\"id\":\"70\",\"num\":\"4\"},{\"id\":\"102\",\"num\":\"23\"},{\"id\":\"112\",\"num\":\"32\"}]}";
    JSONObject rootObject = JsonConverter.parse(propStr, JSONObject.class);
    JSONArray propIds = rootObject.getJSONArray("propIds");
    System.out.println(propIds);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < propIds.size(); i++) {
      JSONObject sonObject = propIds.getJSONObject(i);
      Integer sonId = sonObject.getInteger("id");
      Integer sonNum = sonObject.getInteger("num");
      map.put(sonId, sonNum);
    }
  }

  @Test
  public void test1_23() {
    Map<String, Node> map = new HashMap<>();
    Node node1 = new Node();
    node1.setName("1");
    Node node2 = new Node();
    node2.setName("2");

    map.put("1", node1);
    map.put("2", node2);
    map.entrySet().forEach(entry -> {
      Node value = entry.getValue();
      value.setName(value.getName() + "x");
    });
    for (Map.Entry<String, Node> stringNodeEntry : map.entrySet()) {
      System.out.println(stringNodeEntry.getValue().getName());
      ;
    }
  }

  public static int count(int n) {
    return (int) (Math.sqrt(n) + n % 2);
  }

  @Test
  public void test1_24() {
    boolean ab = checkInProtect(new Date(1609516799000L));
    System.out.println(ab);
    List<Long> offlineNobleIds = JSONArray.parseArray("[7,8]", Long.class);
    for (Long offlineNobleId : offlineNobleIds) {
      System.out.println(offlineNobleId);
    }
  }

  private boolean checkInProtect(Date renewTime) throws BusinessException {
    // 校验是否保护期内（包含未过期）
    Integer protectDays = 10;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    try {
      date = simpleDateFormat.parse("2021-08-25 23:59:59");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DateTime dateTime = new DateTime(date);
    System.out.println(dateTime.minusDays(31));
    DateTime time = dateTime.plusDays(1);
    System.out.println(time.toDate().getTime());
    return time.isAfter(DateTime.now());
  }

  class AExcepetion extends RuntimeException {
    public AExcepetion(String message) {
      super(message);
    }
  }

  class BException extends RuntimeException {
    public BException(String message) {
      super(message);
    }
  }

  class CException extends BException {

    public CException(String message) {
      super(message);
    }
  }

  @Test
  public void test1_25() {
    try {
      if (true) {
        throw new CException("c...");
      }
    } catch (BException e) {
      System.out.println("e...");
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void test1_26() {
    Map<Long, String> map = new HashMap<>();
    map.put(1L, "2");
    map.put(1L, "3");
    map.entrySet().forEach(v -> System.out.println(v.getValue()));
  }

  private static ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 500, 10L, TimeUnit.MINUTES,
      new LinkedBlockingQueue<Runnable>(5000), new ThreadPoolExecutor.DiscardOldestPolicy());

  static {
    StatLog.registerExecutor("dispatcher-fans-handle", executor);
  }

  @Test
  public void test1_27() {
    try {
      List<String> a = Collections.EMPTY_LIST;
      System.out.println(!a.contains("a"));
      a.get(1);
    } catch (Exception e) {
      System.out.println("logger error...");
    }
    List<UserDeviceInfo> validAndNotificationOnDevices = new ArrayList<>();
    List<UserDeviceInfo> finalValidAndNotificationOnDevices = validAndNotificationOnDevices;
    executor.execute(() -> {
      System.out.println("error1///");
      if (CollectionUtils.isNotEmpty(finalValidAndNotificationOnDevices)) {
        System.out.println("error2///");
      }
    });
    System.out.println("execute...");
  }

  @Test
  public void test1_28() {
    Long a = null;
    List<Long> batchFansUids = new ArrayList<>();
    batchFansUids.add(a);
    Long lastFollowerUid = batchFansUids.get(batchFansUids.size() - 1);
    System.out.println(lastFollowerUid);
    // batchFansUids = userRelationService.getFansFromDB4Push(pageParam,
    // (lastFollowerUid + 1), higherFansUidExclusive);
  }

  @Test
  public void test1_29() {
    List<Long> batchFansUids = new ArrayList<>();
    // batchFansUids.add(1L);
    batchFansUids.add(2L);
    // batchFansUids.add(3L);
    // batchFansUids.add(4L);

    if (CollectionUtils.isNotEmpty(batchFansUids)) {
      // List<Long> tempUids = batchFansUids;

      List<Long> copy = new ArrayList<>(batchFansUids);
      op(copy);
      System.out.println(batchFansUids.size());
      Long ba = batchFansUids.get(batchFansUids.size() - 1);
      System.out.println(ba);
    }
  }

  public void op(List<Long> ops) {
    ops.remove(2L);
  }

  private static final Logger logger = LoggerFactory.getLogger(SimpleTest1.class);

  @Test
  public void test1_30() {
    String title = "【勉強٩(ˊᗜˋ*)و】テストや入試ファイトです(❁⃘p>ω<)尸\" ﾌﾚｰﾌﾚｰ☆".replace('\"', '"');
    System.out.println(title);
    String s = JSON.toJSONString(title);
    System.out.println(s);
    JsonElement parse = new JsonParser().parse(s);
    System.out.println(parse);
  }

  @Test
  public void test1_40() {

    for (int i = 0; i < 30; i++) {
      int j = i + 1;
      Node node = null;

      try {
        node = new Node();
        node.setName("node " + i);
        if (i == j) {

        } else {
          Node node1 = node;
          executor.execute(() -> {
            printx(node1, j);
          });
          // Thread.sleep(20);
        }
      } catch (Exception e) {
      }
    }
  }

  public void printx(Node node, int i) {
    System.out.println(node.getName() + ":" + i);
  }

  @Test
  public void test1_41() {
    JsonObject outerData = new JsonObject();
    outerData.addProperty("type", PushType.CUSTOM_MESSAGE.getIndex());
    outerData.addProperty("subType", PushCustomMessageType.ROOM_STAR.getIndex());
    RoomInfo roomInfo = new RoomInfo();
    roomInfo.setActualTime(1647502057322L);
    roomInfo.setCommunicateNumber(0);
    roomInfo.setUid(2044685045812L);
    roomInfo.setTitle("〔〕Near的直播间");
    String title = "〔〕Near的直播间";
    String subTitle = "〔〕Near的直播间";
    String headUrl = "xxxx";
    // outerData.add("userInfo",JSON.toJSONString(userInfo));
    String pushInfoJson = "{\"title\":\"" + title + "\",\"subTitle\":\"" + subTitle + "\",\"type\":0,\"headUrl\":\""
        + headUrl + "\"}";
    System.out.println(pushInfoJson);

    outerData.add("pushInfo", new JsonParser().parse(pushInfoJson));
    outerData.add("roomInfo", new JsonParser().parse(JSON.toJSONString(roomInfo)));
    System.out.println(outerData.toString());

    JSONObject outerData2 = new JSONObject();
    outerData2.put("roomInfo", roomInfo);
    outerData2.put("pushInfo", JSON.parse(pushInfoJson));
    outerData2.put("type", PushType.CUSTOM_MESSAGE.getIndex());
    outerData2.put("subType", PushCustomMessageType.ROOM_STAR.getIndex());

    System.out.println(outerData2.toJSONString());
  }

  @Test
  public void new_test() {
    String key = "eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZSIsImV4cCI6MTY1NTAyNTI3MywiaWF0IjoxNjU0OTM4ODczLCJzdWIiOiIwMDEzMzIuZThmMDk3ODdjZjllNDYzNDg1MDliNmZiODE1NTkxNWIuMDkxNCIsImNfaGFzaCI6Imwzc0QtNUZ1OEJLTFNUcUtfbkJ6ZFEiLCJlbWFpbCI6ImZrZnhjYnRobTRAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NTQ5Mzg4NzMsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZSwicmVhbF91c2VyX3N0YXR1cyI6Mn0";
    byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(key);
    System.out.println(new String(bytes));

    String key2 = "aqSwF7nu5Ed8lJ5ycUu9A1DHZqWXV8iu7_e2JUs1oVf1FPwea-y6qYLvPn_-Zc0ClKJ-h3CUZPWni3d_QBchKcO8YHJNuaiw24xVyCbHlB0L1HBk1qzSF3auzzkU7qHM3W6sr4rz4YKCTDWlVnwBUltog1nmspfghyCUstGN1Tt9dJXhS6fnLDm_-s9uypM6HVLejzBzfskdcOYFrm8d_ImHML9tsdn9i1nxnM8_MAMJnSjboLMQU5q-fiwXwbDQbZtG69dJVAmf6m2U-kAxlK1A43SJpgJ4ltlz9woQfdj_-QUeDLbOfTn03KsDf7od-JaXOwf7iIx4iAHrMo07bw";
    byte[] bytes2 = org.apache.commons.codec.binary.Base64.decodeBase64(key2);
    com.alibaba.fastjson.JSONObject body = com.alibaba.fastjson.JSONObject.parseObject(new String(bytes2));
    String audience = (String) body.get("aud");
    String subject = (String) body.get("sub");
    System.out.println(audience + "---" + subject);
  }

  @Test
  public void new_test2() {
    String accessToken = "eyJraWQiOiJmaDZCczhDIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZSIsImV4cCI6MTY1NTAyNTI3MywiaWF0IjoxNjU0OTM4ODczLCJzdWIiOiIwMDEzMzIuZThmMDk3ODdjZjllNDYzNDg1MDliNmZiODE1NTkxNWIuMDkxNCIsImNfaGFzaCI6Imwzc0QtNUZ1OEJLTFNUcUtfbkJ6ZFEiLCJlbWFpbCI6ImZrZnhjYnRobTRAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NTQ5Mzg4NzMsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZSwicmVhbF91c2VyX3N0YXR1cyI6Mn0.aqSwF7nu5Ed8lJ5ycUu9A1DHZqWXV8iu7_e2JUs1oVf1FPwea-y6qYLvPn_-Zc0ClKJ-h3CUZPWni3d_QBchKcO8YHJNuaiw24xVyCbHlB0L1HBk1qzSF3auzzkU7qHM3W6sr4rz4YKCTDWlVnwBUltog1nmspfghyCUstGN1Tt9dJXhS6fnLDm_-s9uypM6HVLejzBzfskdcOYFrm8d_ImHML9tsdn9i1nxnM8_MAMJnSjboLMQU5q-fiwXwbDQbZtG69dJVAmf6m2U-kAxlK1A43SJpgJ4ltlz9woQfdj_-QUeDLbOfTn03KsDf7od-JaXOwf7iIx4iAHrMo07bw";

    Map<String, String> paraMap = getParaMap(accessToken);
    paraMap.entrySet().forEach(e -> System.out.println("key:" + e.getKey() + ",value:" + e.getValue()));
  }

  public Map<String, String> getParaMap(String accessToken) {
    Map<String, String> result = new HashMap();
    String[] jwt = accessToken.split("\\.");
    byte[] header = org.apache.commons.codec.binary.Base64.decodeBase64(jwt[0].getBytes());
    byte[] playBody = org.apache.commons.codec.binary.Base64.decodeBase64(jwt[1].getBytes());
    com.alibaba.fastjson.JSONObject body = com.alibaba.fastjson.JSONObject.parseObject(new String(playBody));
    String audience = (String) body.get("aud");
    String subject = (String) body.get("sub");
    com.alibaba.fastjson.JSONObject head = com.alibaba.fastjson.JSONObject.parseObject(new String(header));
    System.out.println(head.toJSONString());
    String kid = (String) head.get("kid");
    result.put("audience", audience);
    result.put("subject", subject);
    result.put("kid", kid);
    return result;
  }

  @Test
  public void time_test(){
    ZoneId zoneId = ZoneId.systemDefault();
    zoneId = ZoneId.of("+9");
    long l = LocalDate.now(zoneId).atTime(23, 59, 59).atZone(zoneId).toInstant().toEpochMilli() / 1000;
    System.out.println(l);
    
  }
}
