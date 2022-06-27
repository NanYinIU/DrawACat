package com.uxin.aliyun.logs.handler.access;


import com.uxin.aliyun.logs.handler.Manbo;
import org.springframework.stereotype.Component;

@Component("manboAccessHandler")
public class ManboAccessHandler extends AccessHandler implements Manbo {
    @Override
    public String getLogName() {
        return "hd_manbo_portal_access";
    }
}
