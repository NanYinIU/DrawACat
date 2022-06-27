package com.uxin.aliyun.logs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHandler {
    public static void main(String[] args) {
        String content = "2022-06-16 12:54:25:999 INFO URL:/api/v347/user/login/password remote:192.168.5.8 uid:null header:{\"host\":\"api.kilamanbo.com\",\"x-forwarded-for\":\"58.56.6.194\",\"x-real-ip\":\"58.56.6.194\",\"x-scheme\":\"https\",\"connection\":\"close\",\"content-length\":\"121\",\"request-page\":\"Android_LoginDialogActivity\",\"ua\":\"os=10&vm=Ns6E5HUNFFYxHGmOpVkJgA==&m=BAH3-W59&s=1200x1934&c=2&vc=347&vn=1.3.29&n=kilaaudio&cn=00&cm=00&appid=12&ie=0&net=1&rid=598d3731-b28f-4298-a73f-483430f7cc5e1655355266003&did=X+PBkDo5+1ylzR78mr+A4zKTD+JRgdD5eK4YlkPb6bpw1gZdjPPU2Us7tQ2RPhBJ\",\"_c\":\"20\",\"accept\":\"*/*\",\"identify\":\"hid=Android&uxid=90b48d8e05cfa39ae36d1d56a7398921\",\"appid\":\"12\",\"requestid\":\"588d3731-b28f-4298-a73f-483430f7cc5e1655355266003\",\"isv8aabi\":\"1\",\"content-type\":\"application/x-www-form-urlencoded\",\"accept-encoding\":\"gzip\",\"user-agent\":\"okhttp/4.9.0\"} params:{\"mobile\":\"FfetjLL1S7U77VC8I5DjXQ==\",\"password\":\"EH6w16UjcXyV1kQHVRirWA==\",\"source\":\"86\",\"sign\":\"ba8d9656f2596b1a27df6be585baad74\"}";
        Pattern requestIdPattern = Pattern.compile("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}");

        Matcher matcher = requestIdPattern.matcher(content);

        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
        }

        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
        }

        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
        }

    }
}
