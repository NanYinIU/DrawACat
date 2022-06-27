package com.uxin.aliyun.logs.handler.error;

import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Manbo;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;


@Component("manboErrorHandler")
public class ManboErrorHandler extends ErrorHandler implements Manbo {
    @Override
    public String getLogName() {
        return "hd_manbo_portal_error";
    }


}
