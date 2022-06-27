package com.uxin.aliyun.logs.handler.info;

import com.uxin.aliyun.logs.handler.Service;
import org.springframework.stereotype.Component;


@Component("serviceInfoHandler")
public class ServiceInfoHandler extends InfoHandler implements Service {
    @Override
    public String getLogName() {
        return "hd_service_live_info";
    }
}
