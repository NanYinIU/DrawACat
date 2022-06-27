package com.uxin.aliyun.logs.handler.access;

import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Kila;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("kilaAccessHandler")
public class KilaAccessHandler extends AccessHandler implements Kila {
    @Override
    public String getLogName() {
        return "hd_portal_access";
    }

}
