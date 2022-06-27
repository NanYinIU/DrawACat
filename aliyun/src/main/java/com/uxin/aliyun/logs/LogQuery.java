package com.uxin.aliyun.logs;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetLogsResponse;

import java.util.Date;

public class LogQuery {
    public static void main(String[] args) throws LogException {
        //阿里云访问密钥AccessKey。更多信息，请参见访问密钥。阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维。
        String accessId = "LTAI5tE8c91rSRPseHPRoKxM";
        String accessKey = "w0jzaQ1iiHgPLIwzfyX3xktBBcdg3O";
        //Project名称。
        String project = "hd-live";
        //日志服务的服务入口。更多信息，请参见服务入口。此处以杭州为例，其它地域请根据实际情况填写。
        String host = "cn-beijing.log.aliyuncs.com";
        //Logstore名称。
        String logStore = "hd_manbo_portal_access";

        //创建日志服务Client。
        Client client = new Client(host, accessId, accessKey);

        //在指定的Logstore内执行query分析。
        try {
            //使用关键字path-0/file-5查询日志。
            String query = "\"login\"";

            int from = Long.valueOf(System.currentTimeMillis() / 1000).intValue() - 86400 ;
            int to = Long.valueOf(System.currentTimeMillis() / 1000).intValue() - 80000;
            //该示例中，query为查询语句，接口中line参数控制返回日志条数，line取值为3。
            GetLogsResponse logsResponse = client.GetLogs(project, logStore, from, to, "", query + "|with_pack_meta", 3, 0,true);
            System.out.println(JSON.toJSON(logsResponse));

        } catch (LogException e) {
            System.out.println("LogException e :" + e.toString());
            System.out.println("error code :" + e.GetErrorCode());
            System.out.println("error message :" + e.GetErrorMessage());
            throw e;
        }
    }
}
