package com.uxin.aliyun.logs.handler.info;

import com.aliyun.openservices.log.common.LogItem;
import com.uxin.aliyun.logs.handler.Manbo;
import com.uxin.util.LogContents;
import org.springframework.stereotype.Component;

@Component("manboInfoHandler")
public class ManboInfoHandler extends InfoHandler implements Manbo {
    @Override
    public String getLogName() {
        return "hd_manbo_portal_info";
    }

}
