package cat.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uxin.commons.crypto.RSAEncrypt;
import com.uxin.commons.push.common.PushFlagEnum;
import com.uxin.commons.string.StringUtil;
import com.uxin.commons.util.CollectionUtils;
import com.uxin.zb.biz.commons.model.ThirdChannelType;
import com.uxin.zb.fans.group.service.model.FansGroupBannerList;
import com.uxin.zb.gacha.model.UserBackpackInfo;
import com.uxin.zb.gacha.model.UserBackpackItemStatus;
import com.uxin.zb.user.service.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import redis.clients.jedis.Tuple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2021-08-26
 */
public class UxinTest {

    @Test
    public void test1(){

        DateTime dataTime = new DateTime(1634140798000L);
        dataTime = dataTime.plusDays(3*30);
        System.out.println(dataTime.toDate().getTime());
        // this is test1
    }

    @Test
    public void test1_1(){
        Map<Long, Long> result =  new LinkedHashMap<>();
        Set<Tuple> tuples = new LinkedHashSet<>();
        Tuple tuple1 = new Tuple(String.valueOf(1411033525674573826L),new Double(10));
        Tuple tuple2 = new Tuple(String.valueOf(1411034917243977730L),new Double(5));
        Tuple tuple3 = new Tuple(String.valueOf(1410043621612191746L),new Double(1));
        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);
        Iterator<Tuple> iterator = tuples.iterator();
        System.out.println(JSONObject.toJSONString(tuples));
//        while (iterator.hasNext()) {
//            Tuple next = iterator.next();
//            if (next != null && next.getElement() != null) {
//                result.put(Long.valueOf(next.getElement()), (long) next.getScore());
//            }
//        }

        if(tuples!=null && CollectionUtils.isNotEmpty(tuples)){
            for (Tuple tuple : tuples) {
                System.out.println(tuple.getScore());
                result.put(Long.valueOf(tuple.getElement()), (long) tuple.getScore());
            }
        }

        System.out.println(JSON.toJSONString(result));

    }

    @Test
    public void test2(){
        List<Long> uids = Arrays.asList(0L,1L,2L);
//        Long[] objects = (Long[]) ConvertUtils.convert(uids, Long.class);
//        System.out.println(Arrays.toString(objects));
        String[] objects = uids.stream().map(String::valueOf).toArray(String[]::new);

        System.out.println(Arrays.toString(objects));
    }

    @Test
    public void  test3(){
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        a.add("a");
        b.add("b");

        List<List<String>> c = new ArrayList<>();
        c.add(a);
        c.add(b);
        List<String> collect = c.stream().flatMap(List::stream).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }

    }


    @Test
    public void test4(){
        String expireTime = getExpireTime(DateTime.now().minusDays(1).toDate());
        System.out.println(expireTime);
        int count = 7;
        int pageSize = 5;
        int totalPage = (int) Math.ceil(count * 1.000 / pageSize);
        System.out.println(totalPage);
    }

    private static String getExpireTime(Date memberExpireTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(memberExpireTime);

        return dateString;
    }

    @Test
    public void test5(){

        Node node = new Node();
        if(parseNode(node)){
            System.out.println("....."+node.toString());
        }
        List<String> s1 = new ArrayList<>();
        s1.add("s1");
        s1.add("s2");
        List<String> s2 = new ArrayList<>();
        s2.add("s1");
        s2.add("s2");


        parseS(s1);

        boolean i = false;
        parseI(i);
        System.out.println(i);
        System.out.println(s1.size());
//        parseS(s2);
        System.out.println(s2.size());

    }

    private void parseI(boolean i) {
        i = true;
    }

    private void parseS(List<String> s1) {
        s1.add("s3");
//        s1.clear();
    }

    private boolean parseNode(Node node) {
        node.setName("test");
        Node node1 = new Node();
        node1.setName("test2");
        node = node1;
        return true;
    }

    @Test
    public void test6(){
        Long code = 1621143669660713002L;
        double sqrt =code;
        System.out.println(sqrt);
        double pow = Math.pow(2, 64);
        System.out.println(pow);
        System.out.println(Math.max(pow,code));

    }

    @Test
    public void test7(){
        String content = "push_message_index_user_238081074_23808107";

        String pattern = "^push_message_index_user_[0-9]{1,13}_[0-9]{1,13}$";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("isMatch");
    }

    @Test
    public void test8(){
        List<String> s1 = new ArrayList<>();
        s1.add("s1");
        s1.add("s2");
        s1.add("s3");

        List<String> s2 = new ArrayList<>();
        s2.add("s1");
        s2.add("s2");
        s2.add("s4");

        s1.retainAll(s2);

        s1.forEach(System.out::println);
        System.out.println("--------");
        s2.forEach(System.out::println);

        caseTest(ThirdChannelType.TALKER);
    }

    private boolean caseTest(ThirdChannelType thirdChannelType){
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


    @Test
    public void test9(){
        rsa();
    }

    public void rsa()
    {
        // product
        String pwd = "IIoJzlXzbBeearavM4JpG8KrOZ5BveEYaIwQZOpngxzLRBXsdEy6YIDDQ4k9cyhimKpUvKvGE6LQMj0W7M7Sxeq8uiPTBswc5JqO99Yh+/8Q6pK/w38mVUhanJ5gUBYxhMMiMKzGtqBBPbAU8+Xey4XdQWvmjnBfQwuWZRUh4I8=";
        // test
        pwd = "W+HVU1yerswj3EYbqjScGNhwshE9U+yuPPC6yvs077pPCcmK4+/sNKprxbVsWB67c4vn9g3oG1oEcpVzLKyB2Z2xihsM2g4OH2PlWUH/Bnkmqom1Zf+1Huwb8m7WgfpStlOEySSJprRg/xIFID9/kl/Sa098sxWdzCtGlrfN+Sw=";
        String password = null;
        try {
            password = new RSAEncrypt().decrypt(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(password);

        Properties properties = new Properties();
        properties.put("111", "2222");
        System.out.println(properties);
    }

    @Test
    public void test10(){
        getPushThirdPartyChannelPushFlag(2);
        String a = " ";
        String[] split = a.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        List<String> strings = Arrays.asList(a.split(","));
        System.out.println(strings);
    }

    public String getPushThirdPartyChannelPushFlagFilter() {
        return "{\"6\":\"55,30\"}";
    }


    public List<PushFlagEnum> getPushThirdPartyChannelPushFlag(Integer thirdFactoryEnum){
        if (thirdFactoryEnum != null && StringUtil.isNotEmpty(getPushThirdPartyChannelPushFlagFilter())) {
            String flags = JSONObject.parseObject(getPushThirdPartyChannelPushFlagFilter()).getString(thirdFactoryEnum.toString());
            System.out.println(flags);
            String[] flagsArr = StringUtils.split(flags, ",");
            System.out.println(flagsArr.length);
            List<PushFlagEnum> result = new ArrayList<>(3);
            for (String flagId : flagsArr) {
                PushFlagEnum pushFlagEnum = PushFlagEnum.get(Integer.valueOf(flagId));
                if (pushFlagEnum != null) {
                    result.add(pushFlagEnum);
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    @Test
    public void test11() throws ParseException {
        int a = 2;
        int b = 4;
        if(a > b){

        }else if (a*b==8){
            System.out.println("B");
        }
    }


    public void throwNPE(){
        throw new NullPointerException();
    }

  public String getFansGroupBannerList() {
    return "{\"0\":[{\"id\":1,\"picUrl\":\"https://img.hongrenshuo.com.cn/shouhusuipian.png\",\"link\":\"http://playtest.uxin.com/GuardShard/index\"}],\"12\":[\n"
        + "{\"id\":1,\"picUrl\":\"https://img.hongrenshuo.com.cn/shouhusuipian.png\",\"link\":\"https://live.hongdoulive.com/GuardShard/index\"}\n"
        + "],\"666\":[]}";
  }

  @Test
  public void test0_0_0() {
    getFansGroupBannerListByAppId("0");
  }

  public List<FansGroupBannerList> getFansGroupBannerListByAppId(String appId) {
    if (org.apache.commons.lang.StringUtils.isEmpty(appId)) {
      return Collections.emptyList();
    }
    String fansGroupBannerList = getFansGroupBannerList();
    if (org.apache.commons.lang.StringUtils.isEmpty(fansGroupBannerList)) {
      return Collections.emptyList();
    }
    Map map = com.alibaba.fastjson.JSONObject.parseObject(fansGroupBannerList, Map.class);
    Object o = map.get(appId);
    if (o == null) {
      return Collections.emptyList();
    }
    List<FansGroupBannerList> fansGroupBannerLists = com.alibaba.fastjson.JSONObject.parseArray(o.toString(),
        FansGroupBannerList.class);
    System.out.println(fansGroupBannerLists);
    return fansGroupBannerLists;
  }

  @Test
  public void tes_1_1() {
    UserInfo userInfo = new UserInfo();
    // userInfo.setId(1111L);
    Long num = Optional.ofNullable(userInfo.getId()).orElse(0L);
    System.out.println(num);
  }

  @Test
  public void test_get_week() {
    String weekString = buildWeekTimeByZoneWithOffset(ThirdChannelType.HONGDOU, -1);
    System.out.println("***** get lastWeek is: " + weekString);
  }

  @Test
  public void test_get_target_time_week() {
    String weekString = buildWeekTimeByZoneWithTimeStamp(ThirdChannelType.HONGDOU, 1664720510000L);
    System.out.println("***** get week is: " + weekString);
  }

  public static String buildWeekTimeByZoneWithOffset(ThirdChannelType thirdChannelType, Integer offset) {
    DateTime now = DateTime.now().plusWeeks(offset);
    if (thirdChannelType == ThirdChannelType.KLIVE) {
      now = DateTime.now().plusWeeks(offset)
          .toDateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(ZoneId.SHORT_IDS.get("JST"))));
    }
    int year = now.getWeekyear();
    int week = now.getWeekOfWeekyear();
    return String.valueOf(year).concat(String.valueOf(week));
  }

  /**
   * 根据时间戳获取周数
   * @param thirdChannelType
   * @param timeStamp
   * @return
   */
  public static String buildWeekTimeByZoneWithTimeStamp(ThirdChannelType thirdChannelType, Long timeStamp) {
    DateTime dt = new DateTime(timeStamp);
    int year = dt.getWeekyear();
    int week = dt.getWeekOfWeekyear();
    return String.valueOf(year).concat(String.valueOf(week));
  }

  @Test
  public void test_double_2_int() {

    Double d = new Double(0.2);
    System.out.println(d);
    System.out.println(Math.round(d));
    System.out.println(d.longValue());
    System.out.println(d.intValue());
  }

  @Test
  public void test_time_format() {
    String formatDate = "2022010112";
    DateTime dateTime = DateTimeFormat.forPattern("yyyyMMddHH")
        .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(ZoneId.SHORT_IDS.get("JST"))))
        .parseDateTime(formatDate);
    System.out.println(dateTime);
    System.out.println(dateTime.toString("yyyyMMddHH"));
    // set dateTime to max in this day
    dateTime = dateTime.millisOfDay()
            .withMaximumValue();
    // format formatDate to yyyyMMdd
    formatDate = dateTime.toString("yyyyMMdd");

  }

  @Test
  public void test_enum(){
//    List<GachaItemType> defaultList = PeriodGachaItemType.getDefaultList();
//    System.out.println(defaultList);
//    String s = BanPanelOptionsEnum.FOREVER.toString();
//    System.out.println(s);
//    List<BanPanelOptionsEnum> from = BanPanelOptionsEnum.from(ThirdChannelType.KLIVE);
//    System.out.println(JSON.toJSONString(from));
    List<BanPanelOption> banPanelOptionsJsonFromSerializeMap = BanPanelOptionsEnum.getBanPanelOptionsJsonFromSerializeMap(ThirdChannelType.KLIVE);
    System.out.println(JSON.toJSONString(banPanelOptionsJsonFromSerializeMap));
//    List<BanPanelOption> banPanelOptionsJsonFromSerializeMap1 = BanPanelOptionsEnum.getBanPanelOptionsJsonFromSerializeMap(ThirdChannelType.KLIVE);
//    System.out.println(JSON.toJSONString(banPanelOptionsJsonFromSerializeMap1));

  }

  @Test
  public void test1_1_1(){
    List<UserBackpackInfo> infos = new ArrayList<>();
    // mock data
    UserBackpackInfo info1 = new UserBackpackInfo();
    info1.setId(1L);
    info1.setCreateTime(new Date(1L));
    info1.setNum(1);
    info1.setExpireTime(new Date(3L));
    info1.setStatus(UserBackpackItemStatus.ACTIVE);

    UserBackpackInfo info2 = new UserBackpackInfo();
    info2.setId(1L);
    info2.setNum(2);
    info2.setCreateTime(new Date(2L));
    info2.setExpireTime(new Date(3L));
    info2.setStatus(UserBackpackItemStatus.ACTIVE);

    UserBackpackInfo info3 = new UserBackpackInfo();
    info3.setId(3L);
    info3.setNum(2);
    info3.setCreateTime(new Date(3L));
    info3.setExpireTime(new Date(2L));
    info3.setStatus(UserBackpackItemStatus.ACTIVE);

    UserBackpackInfo info4 = new UserBackpackInfo();
    info4.setId(2L);
    info4.setNum(2);
    info4.setCreateTime(new Date(3L));
    info4.setExpireTime(null);
    info4.setStatus(UserBackpackItemStatus.ACTIVE);

    UserBackpackInfo info5 = new UserBackpackInfo();
    info5.setId(2L);
    info5.setNum(1);
    info5.setCreateTime(new Date(5L));
    info5.setExpireTime(null);
    info5.setStatus(UserBackpackItemStatus.ACTIVE);

    infos.add(info1);
    infos.add(info2);
    infos.add(info3);
    infos.add(info4);
    infos.add(info5);

    Map<Long, List<Integer>> collect = infos.stream().collect(Collectors.groupingBy(UserBackpackInfo::getId, Collectors.mapping(UserBackpackInfo::getNum, Collectors.toList())));
    System.out.println(collect);
  }
  private List<UserBackpackInfo> sortByStatus(List<UserBackpackInfo> infos) {
    List<UserBackpackInfo> data = new ArrayList<>();
    data.addAll(infos.stream().filter(v -> UserBackpackItemStatus.ACTIVE.equals(v.getStatus())).sorted(userBackpackInfoComparator).collect(Collectors.toList()));
    data.addAll(infos.stream().filter(v -> UserBackpackItemStatus.SUSPEND.equals(v.getStatus())).sorted(userBackpackInfoComparator).collect(Collectors.toList()));
    data.addAll(infos.stream().filter(v -> UserBackpackItemStatus.NU_ACTIVE.equals(v.getStatus())).sorted(userBackpackInfoComparator).collect(Collectors.toList()));
    data.addAll(infos.stream().filter(v -> UserBackpackItemStatus.SHELF.equals(v.getStatus())).sorted(userBackpackInfoComparator).collect(Collectors.toList()));
    return data;
  }



  private final Comparator<UserBackpackInfo> userBackpackInfoComparator = (o1, o2) -> {
    Date o1Time = o1.getExpireTime();
    Date o2Time = o2.getExpireTime();
    // if status is un active or shelf use create time to sort
    if(UserBackpackItemStatus.NU_ACTIVE.equals(o1.getStatus())
            || UserBackpackItemStatus.SHELF.equals(o1.getStatus())){
      o1Time = o1.getCreateTime();
      o2Time = o2.getCreateTime();
    }
    if (o1Time == null && o2Time == null) {
      return 0;
    }
    if (o1Time == null) {
      return -1;
    }
    if (o2Time == null) {
      return 1;
    }
    if (o1Time.after(o2Time)) {
      return 1;
    }
    if (o2Time.after(o1Time)) {
      return -1;
    }
    return 0;
  };

  @Test
  public void test_week_output(){
    int offset = 0;
    ThirdChannelType thirdChannelType = ThirdChannelType.HONGDOU;
//    DateTimeZone.forTimeZone(TimeZoneUtil.getTimeZone(thirdChannelType))
    DateTime now = DateTime.now().plusWeeks(offset).toDateTime();
    DateTimeFormatter fmt = DateTimeFormat.forPattern("xww");
    System.out.println(now.toString(fmt));
  }

  @Test
  public void bitTest(){
    long uuid_64bit_new = get_uuid_64bit_new(1L);
  }

  private static final int UUID_BUSINESS_NUM = 4;
  private static long serverId = 0;
  private static long haid = 0;
  private static long[] seqidarray = new long[]{0L, 0L, 0L, 0L};
  private static long[] seconds = new long[]{0L, 0L, 0L, 0L};

  public long get_uuid_64bit_new(long id) {
    long uuid = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(+8));

    id = (id < 0 || id > UUID_BUSINESS_NUM - 1) ? 0 : id;
    if (seconds[(int) id] != uuid) {
      seconds[(int) id] = uuid;
      seqidarray[(int) id] = ThreadLocalRandom.current().nextInt() & ((1 << 5) - 1); //seqid start from a random number between 0 and 63
    }

    uuid -= 1262275200L;

    uuid = uuid << 32;
    uuid += serverId << 28;
    uuid += id << 26;
    ++seqidarray[(int) id];
    seqidarray[(int) id] = seqidarray[(int) id] % (1 << 25);
    uuid += seqidarray[(int) id] << 1; //seq num
    uuid += haid; //ha id
    return uuid;
  }


  @Test
  public void test10_10(){
    List<Long> l1 = new ArrayList<>();
    List<Long> l2 =  Collections.emptyList();
    List<Long> l3 =  Collections.emptyList();
    l1.addAll(l2);
    l2.addAll(l3);
    l2.addAll(l1);
  }


  @Test
  public void testLongFormat(){
    String value = "1822336482019377272";
    Set<String> newSet = new HashSet<>();
    newSet.add("-1");
    newSet.add(value);

    List<Long> collect = newSet.stream().map(Long::valueOf).collect(Collectors.toList());
    System.out.println(collect);


  }

}
