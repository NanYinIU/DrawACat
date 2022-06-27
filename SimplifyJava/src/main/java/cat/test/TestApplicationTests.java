package cat.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.uxin.commons.common.User;
import com.uxin.commons.net.http.HttpClientUtil;
import com.uxin.zb.user.service.model.JWK;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.util.stream.Collectors;

class TestApplicationTests {

    private Object Map;

    @Test
    void contextLoads() {
    }

    @Test
    void  testCase1(){
        Set<String> str= new TreeSet<>();
        str.add("0");
        System.out.println(JSONObject.toJSONString(str));
        System.out.println(str.contains(0));

    }

    @Test
    void  testCase2(){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(19);
        List<List<Integer>> lists = subList(list, 3);
        List<Integer> list1 = shuffleAndSubList(list, 3);
        System.out.println(JSONObject.toJSONString(lists));
        System.out.println(JSONObject.toJSONString(list1));


    }

    public static<T>  List<List<T>> subList(List<T> tList, Integer subNum) {
        // 新的截取到的list集合
        List<List<T>> tNewList = new ArrayList<List<T>>();
        // 要截取的下标上限
        Integer priIndex = 0;
        // 要截取的下标下限
        Integer lastIndex = 0;
        // 查询出来list的总数目
        Integer totalNum = tList.size();
        // 总共需要插入的次数
        Integer insertTimes = totalNum / subNum;
        List<T> subNewList = new ArrayList<T>();
        for (int i = 0; i <= insertTimes; i++) {
            // [0--20) [20 --40) [40---60) [60---80) [80---100)
            priIndex = subNum * i;
            lastIndex = priIndex + subNum;
            // 判断是否是最后一次
            if (i == insertTimes) {
                subNewList = tList.subList(priIndex, tList.size());
            } else {
                // 非最后一次
                subNewList = tList.subList(priIndex, lastIndex);

            }
            if (subNewList.size() > 0) {
                tNewList.add(subNewList);
            }
        }
        return tNewList;
    }


    public static <V> List<V> shuffleAndSubList(List<V> list, int subSize) {
        if (list != null && list.size() != 0) {
            Collections.shuffle(list);
            list = list.size() > subSize ? list.subList(0, subSize) : list;
            return list;
        } else {
            return null;
        }
    }

    @Test
    void  testCase3() throws InvalidKeySpecException, NoSuchAlgorithmException {
       //String accessToken= "eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ua2lsYWF1ZGlvLmRlYnVnIiwiZXhwIjoxNjIyMDMxNDUzLCJpYXQiOjE2MjE5NDUwNTMsInN1YiI6IjAwMDA1Ny41NmM3Mjk1YjRmODk0MmYyOGY1MGYzZDc5OWY3NGJlMC4wNzI1IiwiY19oYXNoIjoiNm5lRnZpV2xQcEFvUjl1QnhZNHhOQSIsImVtYWlsIjoiNjU3MjU3ODk3QHFxLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYyMTk0NTA1Mywibm9uY2Vfc3VwcG9ydGVkIjp0cnVlfQ.X8cWF2gjMT98C8uKh7bgGPTFj6pFh2_8wPT5yf3Znmzcd_O-dP7GuoQG504GK0T3eReWtzdcYkJjXivaaWJClISObV6TlZkCJpp7hbqvqzCX1xQAf3SPRsvXObFUZxBnXqr-3OTtU9YD1TpLEh2qg2-jwORQalpMm_nFF0FY4etOsP4QbYjBd8WqyejaAoinZ-23YmMucZx-fUV8tefLV6YiCt1mEh5yO5gcm8S6ton7xWHtEkEuuMm-KR-ez9Nsbqg0yH0vLtiAxW8ba6kxGs89YyvXhGo7z0kdpjy4CyYPiaQnkdGfEMJubE9r5Q3c4LbmiQVHyPAnNJVUhYGA1g";
       String accessToken="eyJraWQiOiJlWGF1bm1MIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ua2lsYW5vdmVsIiwiZXhwIjoxNjIyMTg3Mzg2LCJpYXQiOjE2MjIxMDA5ODYsInN1YiI6IjAwMDA1Ny4yMDc2ODI0NWFhNWI0Yzg1ODM2ZjYyNGUzZDU2YmQ0OS4wNzM2IiwiY19oYXNoIjoieUhRZ1VBWldQN2RBOTFHLUFYMHEtQSIsImVtYWlsIjoiNjU3MjU3ODk3QHFxLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImF1dGhfdGltZSI6MTYyMjEwMDk4NiwidHJhbnNmZXJfc3ViIjoiMDAwMDU3LnIwNGNlYzUyNjg4ODg0YWI4YTE0MzU4ZjUxYTcwYWU4YyIsIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.blMbbHHWdmT6j-tn_T_IoNbcZBbFotiyCixs1BESHrlo-9hl8MwrQ5jLxyGdnXlaitcpuzebXDCZ2dvV5wfqqCLelYj84GhYelw0LVk1RHGu-uWEuQIj2l0FfvX0_EkVrfl4RemR548GLRbNZGQHf9OfwnflIIjT6NDXVCSFlvybN0EE3AfmaIK6QPQ6kGf_f5WutS2NcohpjCofTSafr27o7_C6DbZAoncYc0HzmkU_5VMv_Iq8ME_j2y3mOkcQnJ76x5e0glSBf1_77OhsFnGWEOJQQe4adpXSi5noDsd2VE1QK1yHjgGFqTwK4bEXION6o5Pks5tb8_oeXiwZfg";


       System.out.println("1_:"+JSONObject.toJSONString(getParaMap(accessToken)));
        List<JWK> list=   getJWKs();
        java.util.Map<String, String> para = getParaMap(accessToken);
        Optional<JWK> optionalJWK = list.stream().filter(e -> e.getKid().equals(para.get("kid"))).findFirst();
        JWK jwk = optionalJWK.get();
        System.out.println("2_:"+JSONObject.toJSONString(jwk));
        System.out.println("3_:"+JSONObject.toJSONString(getPublicKey(jwk.getN(), jwk.getE())));
        boolean verify = verify(getPublicKey(jwk.getN(), jwk.getE()), accessToken, para.get("audience"), para.get("subject"));
        System.out.println("5_:"+verify);
    }

    public Map<String,String> getParaMap(String accessToken){
        Map<String,String> result=new HashMap();
        String[] jwt = accessToken.split("\\.");
        byte[] header = Base64.decodeBase64(jwt[0].getBytes());
        byte[] playBody = Base64.decodeBase64(jwt[1].getBytes());
        JSONObject body = JSONObject.parseObject(new String(playBody));
        String audience = (String) body.get("aud");
        String subject = (String) body.get("sub");
        JSONObject head = JSONObject.parseObject(new String(header));
        String kid = (String) head.get("kid");
        result.put("audience",audience);
        result.put("subject",subject);
        result.put("kid",kid);
        System.out.println(body);
        System.out.println(head);
        return result;

    }

    public  static Boolean verify(PublicKey key, String jwt, String audience, String subject) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer("https://appleid.apple.com");
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            System.out.println("4_:"+JSONObject.toJSONString(claim));
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                return true;
            }
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    public  static PublicKey getPublicKey(String n,String e)throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(1,Base64.decodeBase64(n));
        BigInteger bigIntPrivateExponent = new BigInteger(1,Base64.decodeBase64(e));
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private List<JWK> getJWKs() {
        String result =  "{\"keys\":[{\"kty\":\"RSA\",\"kid\":\"YuyXoY\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"1JiU4l3YCeT4o0gVmxGTEK1IXR-Ghdg5Bzka12tzmtdCxU00ChH66aV-4HRBjF1t95IsaeHeDFRgmF0lJbTDTqa6_VZo2hc0zTiUAsGLacN6slePvDcR1IMucQGtPP5tGhIbU-HKabsKOFdD4VQ5PCXifjpN9R-1qOR571BxCAl4u1kUUIePAAJcBcqGRFSI_I1j_jbN3gflK_8ZNmgnPrXA0kZXzj1I7ZHgekGbZoxmDrzYm2zmja1MsE5A_JX7itBYnlR41LOtvLRCNtw7K3EFlbfB6hkPL-Swk5XNGbWZdTROmaTNzJhV-lWT0gGm6V1qWAK2qOZoIDa_3Ud0Gw\",\"e\":\"AQAB\"},{\"kty\":\"RSA\",\"kid\":\"86D88Kf\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"iGaLqP6y-SJCCBq5Hv6pGDbG_SQ11MNjH7rWHcCFYz4hGwHC4lcSurTlV8u3avoVNM8jXevG1Iu1SY11qInqUvjJur--hghr1b56OPJu6H1iKulSxGjEIyDP6c5BdE1uwprYyr4IO9th8fOwCPygjLFrh44XEGbDIFeImwvBAGOhmMB2AD1n1KviyNsH0bEB7phQtiLk-ILjv1bORSRl8AK677-1T8isGfHKXGZ_ZGtStDe7Lu0Ihp8zoUt59kx2o9uWpROkzF56ypresiIl4WprClRCjz8x6cPZXU2qNWhu71TQvUFwvIvbkE1oYaJMb0jcOTmBRZA2QuYw-zHLwQ\",\"e\":\"AQAB\"},{\"kty\":\"RSA\",\"kid\":\"eXaunmL\",\"use\":\"sig\",\"alg\":\"RS256\",\"n\":\"4dGQ7bQK8LgILOdLsYzfZjkEAoQeVC_aqyc8GC6RX7dq_KvRAQAWPvkam8VQv4GK5T4ogklEKEvj5ISBamdDNq1n52TpxQwI2EqxSk7I9fKPKhRt4F8-2yETlYvye-2s6NeWJim0KBtOVrk0gWvEDgd6WOqJl_yt5WBISvILNyVg1qAAM8JeX6dRPosahRVDjA52G2X-Tip84wqwyRpUlq2ybzcLh3zyhCitBOebiRWDQfG26EH9lTlJhll-p_Dg8vAXxJLIJ4SNLcqgFeZe4OfHLgdzMvxXZJnPp_VgmkcpUdRotazKZumj6dBPcXI_XID4Z4Z3OM1KrZPJNdUhxw\",\"e\":\"AQAB\"}]}";
        JSONObject obj = JSONObject.parseObject(result);
        JSONArray array = obj.getJSONArray("keys");
        return new ArrayList<>(JSONArray.parseArray(array.toJSONString(), JWK.class));
    }



    @Test
    void  testCase4(){
        Long TTL_EXPIRATION = 86400L * 180 * 1000;
        //String team_id="6XCYZ2T6S7";//有信
        String team_id="EE463D4K8L";//有咖
        //有读
        //String key_id="FKV3LT6PG8";
        //String key_id="66JKK3K9H6";
        //String client_id="com.uxin.kilanovel";
        //声咚
        //String key_id="3ZGC5FY7WY";
        //String client_id="com.uxin.talker";
        //yk声咚
        //String key_id="8625Q9NW3P";
        //克拉
        //String key_id="84W75394F2";
        String client_id="com.uxin.live";
        String key_id="NQ345TLQ2F";
        //漫播
//        String key_id="H66J4H9U44";
          //String client_id="com.uxin.kilaaudio";
          //String key_id="JPG829ZK8B";

        //有信私钥（有读）
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgtS7+RyLXbMTItg6M\n" +
//                "CeITCvE62fuQYnZrmqT91Op7RjqgCgYIKoZIzj0DAQehRANCAATBaP0Ve5ugnWmD\n" +
//                "CfRnuJJVLyKF3bJVHyxekkWOZHlzAJr/Pm+ypy9G26F2eWpYqeosK4lE9hFrB8MO\n" +
//                "kQALJzB6";
        //有咖私钥（有读）
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQga3FB/sK3sjGGmtfF\n" +
//                "FeKWvKJ3a8UNiMSn3lBFJCAe/B+gCgYIKoZIzj0DAQehRANCAASytQLKS0vRuAiP\n" +
//                "UDnC3Kn9ntqJBrB9C6lEUB/Q1QsHul46TDONnHD3WG2FG7NrohnljQiGuFOl04ss\n" +
//                "vfI66MYo";
        //声咚
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgot3cNgf/CuO30+fm\n" +
//                "nS0JvW4Ld3uU4ioy4fuh4qKs2LWgCgYIKoZIzj0DAQehRANCAAT71+c2ABMieuG1\n" +
//                "NorB9+17wPwhsbGH0YzJ75oSYB30VKF5aOqPlZbxkGIjB/lVi6P88lMnQKnS2HuS\n" +
//                "i/8mr9BD";
        //声咚yk
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgmSppxgl906gal4Ke\n" +
//                "Hix+4oi10v9hGLHzW5+Lsei8shygCgYIKoZIzj0DAQehRANCAATu2OEkrS/68Rsz\n" +
//                "151hK7KPed3grZ3wkmaKhaWSHkctZHRrFuPe2JX750PhMPBdbQNjZgkjalPW39nI\n" +
//                "rBMc4DK1";


        //克拉
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg2gqxP0EubtjVxNBq\n" +
//                "L6NEcBV24PLCUasGU4jv5MfWag2gCgYIKoZIzj0DAQehRANCAAToyCCS150OP7el\n" +
//                "/ZUoxe7LokVgTOBRwCbv8211aYisOGCVn1MNoShg84e8sd65ev7OANeIlPliqr6s\n" +
//                "WlaAFUqu";
        //克拉 youka
        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgMhVD6v9u5NpL4so5\n" +
                "oSt34EJXeBp5uRHmeHUVpXj51jWgCgYIKoZIzj0DAQehRANCAAQsx5MI1f7ClWUW\n" +
                "rqiHiw2JpKgYmlAPV1sWa/7c+1uDQqd1Fm3wYiSdZh2SzVfrYxx7yh5JiwihnouK\n" +
                "tipnAxcH";
        //漫播
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg7R9UbMLnV3nZtBiC\n" +
//                "HEnCpHijpwniY6FbaKdFZdMeCzigCgYIKoZIzj0DAQehRANCAAQTsbLpz2aTlUrB\n" +
//                "ZEHBsp1EZZaGrDYcfxaQmr2M+XJSoQq/cwDuBZD0+kGtdAEAeFmtgQk0VpirpnY+\n" +
//                "1cPjnhry";
        //漫播youka
//        String private_key="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgeGtkVVhx5gmLYrzv\n" +
//                "vG1z/agF77UTQaHfEY2+yRCvx1igCgYIKoZIzj0DAQehRANCAATtdSj7oRmRLWNV\n" +
//                "pqX86cHfaNQfInDCq+CcZXs/jlVD917Kuv2iKvxqKEsskPDTdYyjLK3HoOht1xoO\n" +
//                "L+XX9jcE";
        PrivateKey privateKey = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                    readPrimaryKey(private_key));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        Long expiration = System.currentTimeMillis() + TTL_EXPIRATION;
        System.out.println(expiration);
        Map map = new HashMap();
        map.put("kid",key_id);
//        header.put("alg", SignatureAlgorithm.ES256.getValue());
//        JwtBuilder builder = Jwts.builder()
        JwtBuilder builder = Jwts.builder()
                .setHeader(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration))
                .setIssuer(team_id)
                .setAudience("https://appleid.apple.com")
                .setSubject(client_id)
                .signWith(privateKey,SignatureAlgorithm.ES256);
        System.out.println(builder.compact());
        System.out.println(JSONObject.toJSONString(getParaMap(builder.compact())));
    }

    public static void main(String[] args) {
        TestApplicationTests t = new TestApplicationTests();
        t.testCase4();

        List<JWK> list=   t.getJWKs();
        String accessToken = "eyJraWQiOiJXNldjT0tCIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZS5vdmVyc2VhcyIsImV4cCI6MTY1NjA2MzEwOSwiaWF0IjoxNjU1OTc2NzA5LCJzdWIiOiIwMDAxMTcuMjFkMTA0N2ZlMTM4NDAxYmFlMDI2NTc4NTk0YmY5NGYuMDgzMiIsImNfaGFzaCI6Im5xZlJ2VU15VkhVcTZvdEZrS0o2OHciLCJlbWFpbCI6InFmZDQyOTRuZ2JAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NTU5NzY3MDksIm5vbmNlX3N1cHBvcnRlZCI6dHJ1ZX0.BNHwcgpFBlqJDOEN3XdQXs7LWYwizf7P7cO5qKYTlgdsO6w3v30r_sZYrZVfN5FJiilAkmzy1l3ILeDrRO8pjpJZHsd9DwGudfxrf5M8vISmSKPEm15AZJcf_ZyCBY_lFtrmrB4oGR7hlZOxUI4FDA1xN14TfIkJ07iSkbEkdyNBlsXYlHKuCNrqRH9Eg07_xdrFoFooq84mIbu3DBh6TLfoxVjZv3gtGnzylcLeTyEjFvfRRkzcdsN2yrvrKqCDrMkX7TTcPk7nJ0HQjostRSdw5afkYrAYQSSgCLOpQzYhpd3_dMhXdUpCLf-8WY0GeNElVvv8332n5cO6EUiuOw";
        //解析参数
        Map<String,String> para= t.getParaMap(accessToken);
        System.out.println(para);
        Optional<JWK> optionalJWK = list.stream().filter(e -> e.getKid().equals(para.get("kid"))).findFirst();
        if(optionalJWK.isPresent()){
            JWK jwk = optionalJWK.get();
            boolean verify = false;
            try {
                verify = verify(getPublicKey(jwk.getN(), jwk.getE()), accessToken, para.get("audience"), para.get("subject"));
                System.out.println(verify);
            } catch (NoSuchAlgorithmException e) {
            } catch (InvalidKeySpecException e) {
            }
        }
    }



    public Boolean verify2(PublicKey key, String jwt, String audience, String subject) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer("https://appleid.apple.com");
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
//            logger.info("claims :{}",claim);
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                return true;
            }
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    private byte[] readPrimaryKey(String primaryKey) {
        StringBuilder pkcs8Lines = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new StringReader(primaryKey));
        String line = "";
        try {
            while ((line = rdr.readLine()) != null) {
                pkcs8Lines.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 需要注意删除 "BEGIN" and "END" 行, 以及空格
        String pkcs8Pem = pkcs8Lines.toString();
        pkcs8Pem = pkcs8Pem.replace("-----BEGIN PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");

        // Base64 转码
        return Base64.decodeBase64(pkcs8Pem);
    }


    @Test
    void test33333(){
        String aa="Hi,æ\u0081\u00ADå\u0096\u009Cä½ è§£é\u0094\u0081äº\u0086å\u0085\u008Bæ\u008B\u0089ç¤¼ç\u0089©å\u009B¾é\u0089´!å\u009C¨ç¤¼ç\u0089©å\u009B¾é\u0089´ä¸\u00AD,ä½ é\u0080\u0081å\u0087ºæ\u0088\u0096æ\u0094¶å\u0088°ç\u009A\u0084ç¤¼ç\u0089©é\u0083½å°\u0086å\u008F\u0098æ\u0088\u0090å\u008D¡ç\u0089\u0087è¢«ç\u0082¹äº®å¹¶æ°¸ä¹\u0085è®°å½\u0095ä¸\u008Bæ\u009D¥,æ\u0088\u0090ä¸ºä½ å\u009C¨å\u0085\u008Bæ\u008B\u0089æ\u0098\u009Fç\u0090\u0083ç\u009A\u0084ç¾\u008Eå¥½è§\u0081è¯\u0081ã\u0080\u0082é¦\u0096æ¬¡å¸®ä½ ç\u0082¹äº®å\u008D¡ç\u0089\u0087ç\u009A\u0084äººå°\u0086ä¼\u009Aè¢«æ°¸ä¹\u0085é\u0093\u00ADå\u0088»å\u009C¨å\u008D¡ç\u0089\u0087ä¸\u008Aã\u0080\u0082\\";
        change(aa);
    }


    private static void change(String input) {
        // TODO Auto-generated method stub
        System.out.println("转换前得结果："+input);
        try {
            String output = new String(input.getBytes("iso-8859-1"),"utf-8");
            System.out.println("转换后得结果："+output);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    void test55(){
        String nacosValueExpr="${sync.lighten.count.to.db:}";
        System.out.println(resolveExpr(nacosValueExpr));
    }
    private static final String PLACEHOLDER_PREFIX = "${";

    private static final String PLACEHOLDER_SUFFIX = "}";

    private static final char PLACEHOLDER_MATCH_PREFIX = '{';

    private static final char PLACEHOLDER_MATCH_SUFFIX = '}';

    private static final String VALUE_SEPARATOR = ":";

    private String resolveExpr(String nacosValueExpr) {
        try {
            int replaceHolderBegin = nacosValueExpr.indexOf(PLACEHOLDER_PREFIX) + PLACEHOLDER_PREFIX.length();
            int replaceHolderEnd = replaceHolderBegin;
            for (int i = 0; replaceHolderEnd < nacosValueExpr.length(); replaceHolderEnd++) {
                char ch = nacosValueExpr.charAt(replaceHolderEnd);
                if (PLACEHOLDER_MATCH_PREFIX == ch) {
                    i++;
                } else if (PLACEHOLDER_MATCH_SUFFIX == ch && --i == -1) {
                    break;
                }
            }
            String replaceHolder = nacosValueExpr.substring(replaceHolderBegin, replaceHolderEnd);
            int separatorIndex = replaceHolder.indexOf(VALUE_SEPARATOR);
            if (separatorIndex != -1) {
                return nacosValueExpr.substring(0, separatorIndex + replaceHolderBegin) + nacosValueExpr.substring(replaceHolderEnd);
            }
            return nacosValueExpr;
        } catch (Exception e) {
            throw new IllegalArgumentException("The expr format is illegal, expr: " + nacosValueExpr, e);
        }
    }

    @Test
    void test99(){
        Double a=-100.0;
        long l = a.longValue();
        System.out.println(l);


    }

    @Test
    void test111(){
        String a="您正享受兑换时额外赠送%s%红豆特权";
        //a = String.format(a, 10);
        String s = a.replace("%s", "10");
        System.out.println(s);


    }

    @Test
    void test112(){
        Long count=1601L;
        int pageSize=500;
        int pageNo=0;
        //double a=count.doubleValue()/Double.valueOf(pageSize);
        int totalPage=(int)Math.ceil(count.doubleValue()/Double.valueOf(pageSize));
        System.out.println(totalPage);

        while (pageNo<=totalPage){
            //System.out.println(pageNo);
            //System.out.println(pageSize);
            int start=pageNo*pageSize;
            int end=start+pageSize-1;
            System.out.println("s:"+start+"_"+"e:"+end);
            pageNo=pageNo+1;
            //pageSize=pageNo*pageSize+pageSize;
        }

        System.out.println("end");


    }


    @Test
    void test113(){
        //String a=null;
        //User user = new User();
        //System.out.println(JSONObject.toJSONString(user));
        int pageNo=0;
        int totalPage=2;
        int pageSize=500;
        while (pageNo<totalPage){
            int start=pageNo*pageSize;
            int end=start+pageSize-1;
            pageNo=pageNo+1;
            System.out.println(start+"---"+end);
        }
    }


    @Test
    void test115(){
        double aa=1/100;
        double a=1;
        double b=100;
        BigDecimal decimal = new BigDecimal(a/b);
        Double v = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100;
        Long r =v.longValue();
        System.out.println(r);
    }

    @Test
    void test116(){
        //System.out.println(1627487999000L>1627535023611L);
        int a=10;
        int b=1;
        while (b<a){
            if (b==5){
                break;
            }
            System.out.println(b);
            b=b+1;
        }
        System.out.println("b="+b);
    }



    @Test
    void test119(){
        Map<Integer, Integer> m = new HashMap<>();
        m.put(1,1);
        m.put(2,1);
        m.put(3,1);
        m.put(4,1);
        long sum = m.values().stream().collect(Collectors.summarizingInt(x -> x.intValue())).getSum();
        System.out.println(m.size());
    }

    @Test
    void test120(){
        //getRadioDramaTopInfos(0);
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.get(3));

    }



    @Test
    void test122(){
     //String accessToken="eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZSIsImV4cCI6MTY0MzQ0MjgyNiwiaWF0IjoxNjQzMzU2NDI2LCJzdWIiOiIwMDAzNjEuMmEzMTA3YzZmOWUyNGViYjljOGUzNDIzYTI4MDRmNDAuMDM0OSIsImNfaGFzaCI6ImE0ZDFleVlpX19tYTk4N3ByY28waUEiLCJhdXRoX3RpbWUiOjE2NDMzNTY0MjYsInRyYW5zZmVyX3N1YiI6IjAwMDM2MS5yODQ4YjRlMWUzYTBjNGU3MGEyMTI1NWQ2ZTFjN2YyNWUiLCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.RS-nxjW561Q1rxs7jADdAlV3BVXdRNVyFCAdPTpRUJodosZmhFSkEWa9bKVdn-MQNZ-Qz3s1PvGtyxNv7TEkfLjX8NHkn7YZrFF6G21y4NXsCoYDmcHFRMyuIYu22ssL0uP9cW_mXG6TC0OwgoewRS8G4SINBvor80PuWKMpFCBmuOZ1adsvegJ3v6tAdIa5-ZIs6D1-Aojo1I597uau0dJw4sRA66K6cd320DMxu0S-HTFNeGcF3k-GZdrIzYyE7C8ypN5G1tX7Rx0zbxfwvDjuXMsMebka30Fu-6voELGRSkONwopUedn6Zn931SnhxtOI5r6sPVj1kDcT0rCiSQ\",\"authorizationCode\":\"ca7a0da26cb6e491e89e3fe32fdbdd92c.0.rtwr.D1K3Uod8XvXav186dJR0jw";
     //String accessToken="eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZSIsImV4cCI6MTY0MzQ0MjgyNiwiaWF0IjoxNjQzMzU2NDI2LCJzdWIiOiIwMDAzNjEuMmEzMTA3YzZmOWUyNGViYjljOGUzNDIzYTI4MDRmNDAuMDM0OSIsImNfaGFzaCI6ImE0ZDFleVlpX19tYTk4N3ByY28waUEiLCJhdXRoX3RpbWUiOjE2NDMzNTY0MjYsInRyYW5zZmVyX3N1YiI6IjAwMDM2MS5yODQ4YjRlMWUzYTBjNGU3MGEyMTI1NWQ2ZTFjN2YyNWUiLCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.RS-nxjW561Q1rxs7jADdAlV3BVXdRNVyFCAdPTpRUJodosZmhFSkEWa9bKVdn-MQNZ-Qz3s1PvGtyxNv7TEkfLjX8NHkn7YZrFF6G21y4NXsCoYDmcHFRMyuIYu22ssL0uP9cW_mXG6TC0OwgoewRS8G4SINBvor80PuWKMpFCBmuOZ1adsvegJ3v6tAdIa5-ZIs6D1-Aojo1I597uau0dJw4sRA66K6cd320DMxu0S-HTFNeGcF3k-GZdrIzYyE7C8ypN5G1tX7Rx0zbxfwvDjuXMsMebka30Fu-6voELGRSkONwopUedn6Zn931SnhxtOI5r6sPVj1kDcT0rCiSQ";
        //String accessToken="eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ua2lsYWF1ZGlvIiwiZXhwIjoxNjQzNDQ0MDU4LCJpYXQiOjE2NDMzNTc2NTgsInN1YiI6IjAwMTk0OC5hNzQwOTYyMzA2ZDA0ZDAzOGI4MGY4YWQ3NTdlYmUxZC4wOTU3IiwiY19oYXNoIjoiTF9vampKMzJBZ0RYOHpTUTZLWm9IUSIsImVtYWlsIjoibTE1MjEwNDE0MzA5QDE2My5jb20iLCJlbWFpbF92ZXJpZmllZCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NDMzNTc2NTgsInRyYW5zZmVyX3N1YiI6IjAwMTk0OC5yMTQ0YmM4ZTg2ZjUzNGI2OTg0ZDc0NmZlM2M5NjY0M2QiLCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.EwkF3Mtpy2cGIPzFWO9uNGjtKIgqb2NwrUlU3uY8OyXpK3Ihg5Mp5M24weTfehv0CLaU2LRAow3HV7A441HrwsFpGgUjs2G0sgoFQITtmW4E8Olnsz9E1AFLqlWwc5BnxWDhxx27D05a5YY2_RdccaxOUqoQqeGByj7lxk88LLHX9B1TRl_iSgAmX1t_cvi_NFabX7SDZC-Qy-ZSa8rqOr6uID6_1OQOUeUky0QdWvOHcHeFhgaDR7EoX4QUWj6lAxZj-a3qAHKHuoXmcUj4BYRhJs6ZKv__FymT-O0vhdDRWphTPwc-mOi2x2GaTGt-Cua6rCk3eV9JAzJCpb6mtQ";
        //String a ="eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ua2lsYWF1ZGlvIiwiZXhwIjoxNjQzNDQ0MDU4LCJpYXQiOjE2NDMzNTc2NTgsInN1YiI6IjAwMTk0OC5hNzQwOTYyMzA2ZDA0ZDAzOGI4MGY4YWQ3NTdlYmUxZC4wOTU3IiwiY19oYXNoIjoiTF9vampKMzJBZ0RYOHpTUTZLWm9IUSIsImVtYWlsIjoibTE1MjEwNDE0MzA5QDE2My5jb20iLCJlbWFpbF92ZXJpZmllZCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NDMzNTc2NTgsInRyYW5zZmVyX3N1YiI6IjAwMTk0OC5yMTQ0YmM4ZTg2ZjUzNGI2OTg0ZDc0NmZlM2M5NjY0M2QiLCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.EwkF3Mtpy2cGIPzFWO9uNGjtKIgqb2NwrUlU3uY8OyXpK3Ihg5Mp5M24weTfehv0CLaU2LRAow3HV7A441HrwsFpGgUjs2G0sgoFQITtmW4E8Olnsz9E1AFLqlWwc5BnxWDhxx27D05a5YY2_RdccaxOUqoQqeGByj7lxk88LLHX9B1TRl_iSgAmX1t_cvi_NFabX7SDZC-Qy-ZSa8rqOr6uID6_1OQOUeUky0QdWvOHcHeFhgaDR7EoX4QUWj6lAxZj-a3qAHKHuoXmcUj4BYRhJs6ZKv__FymT-O0vhdDRWphTPwc-mOi2x2GaTGt-Cua6rCk3eV9JAzJCpb6mtQ";
       String a="eyJraWQiOiJXNldjT0tCIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLnV4aW4ubGl2ZSIsImV4cCI6MTY0MzQ0Mjc5OCwiaWF0IjoxNjQzMzU2Mzk4LCJzdWIiOiIwMDA5NTYuZGYwNGNjZDA2NDM2NDlhNGFhYjY3ZmE0MjU4Zjc5Y2UuMDQxOSIsImNfaGFzaCI6Ill6aGc2dFdQb2ZGTHBvZWt0cjBXZUEiLCJlbWFpbCI6IndwYnFydnhuemNAcHJpdmF0ZXJlbGF5LmFwcGxlaWQuY29tIiwiZW1haWxfdmVyaWZpZWQiOiJ0cnVlIiwiaXNfcHJpdmF0ZV9lbWFpbCI6InRydWUiLCJhdXRoX3RpbWUiOjE2NDMzNTYzOTgsInRyYW5zZmVyX3N1YiI6IjAwMDk1Ni5yZTlkZTZiZGI4ZjYxNDlhNDk5NGIxZTFjMTUzMWZlMjQiLCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.E-eY3Ha7Pm_Kg45ZL50PPAQwqugi0hVGmKGgKUoXQCN_Le2T1bBkbDbkNRlrp9iXR4N5yJ6nrl53pc0JcixGIY9P068e3-JS66qR3W-mhgHSR2URMzgYNzYszvrV9dr9j1_5e0kvU-KbmYqCeT5zeWiMy5FnV93LLEwPvWNfoxbvytWvYLtJnPBjIKzb0XZZiie4EWrN7rxrtGs95AsY03KFz7khBiuPWShBWb9dvsAnEVHRvn2xLbZWqaoG_kePsk_eKSMJnJvScjaTDi8uxzQmtMjfdaGw_B6ZcrpXhgi-db1ZoMQOWONJk1BeGgqerz8ZnqR38tHvDstGBOvd8A";
        Map<String,String> result=new HashMap();
            String[] jwt = a.split("\\.");
            byte[] header = Base64.decodeBase64(jwt[0].getBytes());
            byte[] playBody = Base64.decodeBase64(jwt[1].getBytes());
            JSONObject body = JSONObject.parseObject(new String(playBody));
            String audience = (String) body.get("aud");
            String subject = (String) body.get("sub");
            JSONObject head = JSONObject.parseObject(new String(header));
            String kid = (String) head.get("kid");
            result.put("audience",audience);
            result.put("subject",subject);
            result.put("kid",kid);

        System.out.println(JSONObject.toJSONString(result));

        }




    @Test
    void test124(){
        System.out.println(getGoldActivityIconByAppId("0","1"));

    }


    public String getGoldActivityIconByAppId(String appId,String clientType){
        if (appId==null || clientType==null){
            return "";
        }
        String goldActivityIcon = "{\"0\":{\"1\":\"https://img.hongrenshuo.com.cn/0_hublist_activity.png\",\"2\":\"\",\"3\":\"\"},\"12\":{\"19\":\"\",\"20\":\"\"},\"666\":\"\"}";

        if (StringUtils.isNotBlank(goldActivityIcon)){
            Map map = JSONObject.parseObject(goldActivityIcon, Map.class);
            Object o = map.get(appId);
            if (!ObjectUtils.isEmpty(o)){
                Map res = JSONObject.parseObject(JSONObject.toJSONString(o), Map.class);
                if (!CollectionUtils.isEmpty(res)){
                    Object obj = res.get(clientType);
                    if (!ObjectUtils.isEmpty(obj)){
                        return (String) obj;
                    }
                }
            }
        }
        return "";
    }


    @Test
    void test125(){
       Long a=1234567890123L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 30000; i++) {
            encrypt16(String.valueOf(a));
            //System.out.println(encrypt16(String.valueOf(a)));
        }

        System.out.println(System.currentTimeMillis()-start);


    }



    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }


    public static String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }




    @Test
    void test127(){
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(3);
        List<String> list1 = new ArrayList<>();
        list.stream().forEach(integer -> {
            list1.add(JSONObject.toJSONString(integer));
        });
        String [] values = new String[list1.size()];
        list1.toArray(values);

        System.out.println(JSONObject.toJSONString(values));
    }



    @Test
    void test128(){
        int start=1;
        int end=20;
        List<Integer> list = new ArrayList<>();
        while (end>start){
            start++;
            list.add(start);
        }
        System.out.println(JSONObject.toJSONString(list));

    }

    @Test
    void test129(){
        String s="[{\"type\":\"4\",\"id\":\"13\",\"num\":\"1\"},{\"type\":\"8\",\"id\":\"14\",\"num\":\"1\"},{\"type\":\"4\",\"id\":\"15\",\"num\":\"1\"}]";
        String s1="[{\\\"type\\\":\\\"4\\\",\\\"id\\\":\\\"13\\\",\\\"num\\\":\\\"1\\\"},{\\\"type\\\":\\\"8\\\",\\\"id\\\":\\\"14\\\",\\\"num\\\":\\\"1\\\"},{\\\"type\\\":\\\"4\\\",\\\"id\\\":\\\"15\\\",\\\"num\\\":\\\"1\\\"}]";
        System.out.println(JSONObject.toJSON(s).toString());

        //List<FansInfo> fansInfos = JSONObject.parseArray(s1, FansInfo.class);
        //System.out.println(JSONObject.toJSONString(fansInfos));
    }

    @Test
    void test130(){
       String s="%7B%22enterRoomInfo%22%3A%7B%22isFollow%22%3A1%2C%22message%22%3A%22%E4%BB%8E%E7%83%AD%E9%97%A8%E7%9B%B4%E6%92%AD%E4%B8%AD%E5%A4%A7%E6%AD%A5%E6%B5%81%E6%98%9F%E5%9C%B0%E8%B5%B0%E4%BA%86%E8%BF%9B%E6%9D%A5%22%7D%7D\",\"m\":0,\"s\":\"2b7ca5fdaf13013600d869e3fad5eb66";
       String ss="%7B%22enterRoomInfo%22%3A%7B%22isFollow%22%3A1%2C%22fansGroupInfo%22%3Anull%2C%22message%22%3A%22%E4%BB%8E%E7%83%AD%E9%97%A8%E7%9B%B4%E6%92%AD%E4%B8%AD%E5%A4%A7%E6%AD%A5%E6%B5%81%E6%98%9F%E5%9C%B0%E8%B5%B0%E4%BA%86%E8%BF%9B%E6%9D%A5%22%7D%7D\",\"m\":0,\"s\":\"e22c94dc402134c452010e36bc67dae2\",\"f\":1620757057911128068";
       String sss="%7B%22enterRoomInfo%22%3A%7B%22isFollow%22%3A1%2C%22fansGroupInfo%22%3Anull%2C%22message%22%3A%22%E4%BB%8E%E7%83%AD%E9%97%A8%E7%9B%B4%E6%92%AD%E4%B8%AD%E5%A4%A7%E6%AD%A5%E6%B5%81%E6%98%9F%E5%9C%B0%E8%B5%B0%E4%BA%86%E8%BF%9B%E6%9D%A5%22%7D%7D";
       String ssss="%7B%22enterRoomInfo%22%3A%7B%22isFollow%22%3A1%2C%22fansGroupInfo%22%3A%7B%22fansGroupName%22%3A%22%E5%AE%88%E6%8A%A4%E5%9B%A2%22%2C%22styleId%22%3A1%2C%22isBuyFansGroup%22%3Atrue%2C%22level%22%3A2%2C%22fansGroupMedalStatus%22%3A1%7D%2C%22message%22%3A%22%E4%BB%8E%E7%83%AD%E9%97%A8%E7%9B%B4%E6%92%AD%E4%B8%AD%E5%A4%A7%E6%AD%A5%E6%B5%81%E6%98%9F%E5%9C%B0%E8%B5%B0%E4%BA%86%E8%BF%9B%E6%9D%A5%22%7D%7D\",\"m\":0,\"s\":\"d919d30ff6f532cb3136255f0c54b013\",\"f\":1620757057911128068";
       String tt="%7B%22enterRoomInfo%22%3A%7B%22isFollow%22%3A1%2C%22fansGroupInfo%22%3A%7B%22fansGroupName%22%3A%22%E5%AE%88%E6%8A%A4%E5%9B%A2%22%2C%22styleId%22%3A1%2C%22isBuyFansGroup%22%3Atrue%2C%22level%22%3A1%2C%22fansGroupMedalStatus%22%3A1%7D%2C%22message%22%3A%22%E4%BB%8E%E7%83%AD%E9%97%A8%E7%9B%B4%E6%92%AD%E4%B8%AD%E5%A4%A7%E6%AD%A5%E6%B5%81%E6%98%9F%E5%9C%B0%E8%B5%B0%E4%BA%86%E8%BF%9B%E6%9D%A5%22%7D%7D\",\"m\":0,\"s\":\"df355ddfe91e60232060645749edc2ac";
       String s1 = urlDecodeUTF8(tt);
        System.out.println(s1);
    }

    public static String urlDecodeUTF8(String s) {
        if (s != null && s.length() > 0) {
            try {
                return URLDecoder.decode(s, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
            }
        }

        return s;
    }

    @Test
    void test131(){
        List<Long> list = getfansGroupTaskGoodsIds("2", "0");
        System.out.println(JSONObject.toJSONString(list));
        Integer goodsId=401037;
        System.out.println(list.contains(Integer.valueOf(goodsId).longValue()));
    }

    public List<Long> getfansGroupTaskGoodsIds(String type,String appId){
        try{
            if (StringUtils.isEmpty(type) || StringUtils.isEmpty(appId)){
                return Collections.EMPTY_LIST;
            }
            String goodsIdStr ="{\"0\":{\"2\":[401037],\"3\":[61651]},\"666\":{\"2\":[61383],\"3\":[61651]}}";
            if(StringUtils.isBlank(goodsIdStr)){
                return Collections.EMPTY_LIST;
            }
            JSONObject jsonObject = JSONObject.parseObject(goodsIdStr);
            JSONObject appJson = jsonObject.getJSONObject(appId);
            if(appJson == null){
                return Collections.EMPTY_LIST;
            }
            JSONArray jsonArray = appJson.getJSONArray(type);
            if(jsonArray == null || jsonArray.size() == 0){
                return Collections.EMPTY_LIST;
            }
            return jsonArray.toJavaList(Long.class);
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }


    @Test
    void test132(){
        String s="{\"0\":129,\"666\":0}";

        java.util.Map map = JSONObject.parseObject(s, java.util.Map.class);
       List<Integer> list = (List<Integer>) map.values().stream().collect(Collectors.toList());
       System.out.println(JSONObject.toJSONString(list));
    }
    @Test
    void test133(){
        long t=1654047601742L;
        Date date = new Date(t);
        System.out.println(date);

        System.out.println(date.getTime());
    }



}
