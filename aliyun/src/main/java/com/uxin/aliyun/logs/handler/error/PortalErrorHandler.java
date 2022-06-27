package com.uxin.aliyun.logs.handler.error;

import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Kila;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;


@Component("portalErrorHandler")
public class PortalErrorHandler extends ErrorHandler implements Kila {
    @Override
    public String getLogName() {
        return "hd_portal_live_error";
    }

}
