package com.uxin.util;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
public class LogContents {

    private String hostName;

    private String hostIp;

    private String requestId;

    private Long logMills;

    private String content;

    private String packId;

    private String packMeta;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(new DateTime(logMills).toString("yyyy-mm-DD HH:mm:ss:SSS"));
        sb.append(hostName).append('-');
        sb.append(hostIp).append('-');
        sb.append(requestId).append('-');
        sb.append(content);
        return sb.toString();
    }
}
