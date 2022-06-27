package com.uxin.aliyun.logs.handler.info;

import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Kila;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;


@Component("portalInfoHandler")
public class PortalInfoHandler extends InfoHandler implements Kila {
    @Override
    public String getLogName() {
        return "hd_portal_live_info";
    }

}