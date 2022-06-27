package com.uxin.aliyun.logs.handler.error;

import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Service;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("serviceErrorHandler")
public class ServiceErrorHandler extends ErrorHandler implements Service {

    private final List<String> stackFeature = Arrays.asList("Got unchecked and undeclared exception" ,"java.util.concurrent.TimeoutException");

    @Override
    public String getLogName() {
        return "hd_service_live_error";
    }


    @Override
    public List<String> getStackFeature() {
        return stackFeature;
    }
}
