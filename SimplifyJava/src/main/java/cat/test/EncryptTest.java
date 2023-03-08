package cat.test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.uxin.commons.crypto.AESUtil;
import com.uxin.commons.json.JsonConverter;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;

public class EncryptTest {

    @Test
    public void testAESEncrypt() {
        try {
            String afterEncryptString = AESUtil
                    .encryptByLogin("{\"orderType\":\"24\",\"quantity\":\"2\",\"receiveId\":\"2852099473410\"}");
            System.out.println("afterEncryptString:" + afterEncryptString);
            String prevencode = prevEncoderString(afterEncryptString);
            System.out.println("prevencode:" + prevencode);
            String prevdecode = prevDecryptString("UTJVWVhrRWVvNkkwU2Vxdk5GbXRhY0dSOGZFeU9RQ0NMTnZackRGSkx2LzNjSFpyaWd2ZzkvQ0VZZFRTWndRSTBuV0ZMVFY3V2FSQ3VUcWxuZkFqMW40YkovSzhlSnd4c1J1SnBWZy9rWmdEcHNlNmt1WEFKbHNpOE1ya0gzV1I=");
            System.out.println("prevdecode:" + prevdecode);
            String content = AESUtil.decryptByLogin(prevdecode);
            System.out.println("after aes decrypt content:" + content);
        } catch (Exception e) {

        }
    }

    Map<String, Object[]> params = new HashMap<>();

    public void setParameter(String key, Object value) {
        params.put(key, new Object[] { value });
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        parameterMap.forEach(this::setParameter);
    }

    public Map<String, Object[]> getParam() {
        return params;
    }

    @Test
    public void parseJsonMap() {
        String afterDecrypt = "{\"title\":\"新的创建图文接\",\"introduce\":\"新的创建图文接口\",\"source\":2}";
        Map<String, Object> objectObjectMap = JsonConverter.parseMap(afterDecrypt);
        System.out.println(objectObjectMap);
        EncryptTest encryptTest = new EncryptTest();
        encryptTest.setParameterMap(objectObjectMap);
        System.out.println(encryptTest.getParam());
    }

    private String prevDecryptString(String content) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(content);
        return new String(decode, StandardCharsets.UTF_8);
    }

    private String prevEncoderString(String content) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = new String(encoder.encode(content.getBytes()), StandardCharsets.UTF_8);
        return encode;
    }

    @Test
    public void testDateDis() {
        DateTime dataTime = new DateTime(1666525946000L);
        dataTime = dataTime.minusDays(31);
        System.out.println(dataTime.toString());
    }

    @Test
    public void testSplict() {
        String requestURI = "/oms/v1/order/oms/create";
        StringBuilder URI = new StringBuilder();
        String[] requestUriArray = StringUtils.splitByWholeSeparator(requestURI, "/");
        // uri格式为/api/v1/order/create，去掉 /v1/
        String prefix = "";
        if (requestUriArray.length > 0) {
            prefix = requestUriArray[0];
        }
        URI.append(prefix);
        for (int index = 2; index < requestUriArray.length; index++) {
            URI.append("/").append(requestUriArray[index]);
        }

        System.out.println(URI.toString());
    }

}
