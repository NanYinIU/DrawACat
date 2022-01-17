package cat.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uxin.commons.util.CollectionUtils;
import com.uxin.zb.biz.commons.model.ThirdChannelType;
import com.uxin.zb.user.service.enums.PushPlatform;
import org.apache.commons.beanutils.ConvertUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import redis.clients.jedis.Tuple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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


}