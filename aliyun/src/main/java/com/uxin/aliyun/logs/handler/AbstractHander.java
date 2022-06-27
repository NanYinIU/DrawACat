package com.uxin.aliyun.logs.handler;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.response.GetContextLogsResponse;
import com.aliyun.openservices.log.response.GetHistogramsResponse;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.uxin.util.EndPointSetting;
import com.uxin.util.LogContents;
import com.uxin.util.PageParam;
import com.uxin.util.QueryResult;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractHander {

    public String CONTENT = "content" , HOSTNAME = "__tag__:__hostname__" , PACKID = "__tag__:__pack_id__", PACKMETA = "__pack_meta__" , projectName = "hd-live" ;

    private Client client = new Client(EndPointSetting.HOST, EndPointSetting.ACCESSID, EndPointSetting.ACCESSKEY);

    public Pattern requestIdPattern = Pattern.compile("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}");





    public abstract String getLogName();


    public String getTopic(){
        return "";
    }

    public List<String> getStackFeature(){
        return null;
    }


    public QueryResult logQuery(PageParam pageParam ,QueryResult result ) throws Exception{

        GetLogsResponse response = null;

        result = Optional.ofNullable(result).orElseGet(QueryResult::new);

        // 初始偏移量
        int offset = pageParam.getOffset();
        do {
            do {
                response = client.GetLogs(projectName, getLogName(), pageParam.getBeginTime(), pageParam.getEndTime(), getTopic(),pageParam.getQuery() + "|with_pack_meta", pageParam.getPageSize(),offset,pageParam.isResver());
            }while ( response != null && response.IsCompleted());

            for (QueriedLog log : response.getLogs()) {
                LogContents content = parseLogContent(log.GetLogItem());
                content.setHostIp(log.mSource);
                result.getLogs().add(content);
            }

            offset+=pageParam.getPageSize();

        }while (response != null && pageParam.isAllLogs() && !CollectionUtils.isEmpty(response.getLogs()));

        result.getLogs().sort(Comparator.comparingLong(LogContents::getLogMills));

        return result;
    }

    public QueryResult totalCountQuery(PageParam pageParam,QueryResult result ) throws  Exception{

        GetHistogramsResponse response = null;


        result = Optional.ofNullable(result).orElseGet(QueryResult::new);

        do {
            response = client.GetHistograms(projectName, getLogName(), pageParam.getBeginTime(),pageParam.getEndTime(),getTopic(),pageParam.getQuery());
        }while ( response != null && response.IsCompleted());

        result.setTotalCount(response.GetTotalCount());

        return result;
    }

    public QueryResult logContextQuery(String packId, String packMeta , int beforLine , int backLine) throws Exception {

        GetContextLogsResponse contextLogs = null;

        QueryResult result = new QueryResult();

        do {
            contextLogs = client.getContextLogs(projectName, getLogName(), packId, packMeta,beforLine, backLine);
        }while (!contextLogs.isCompleted());

        if  (contextLogs.getLogs() == null || contextLogs.getLogs().size() < 1) {
            return result;
        }

        for (QueriedLog log : contextLogs.getLogs()) {
            LogContents content = parseLogContent(log.GetLogItem());
            content.setHostIp(log.mSource);
            result.getLogs().add(content);
        }

        return result;
    }

    public LogContents parseLogContent(LogItem logItem) {
        LogContents logContents = new LogContents();
        for (LogContent mContent : logItem.mContents) {

            if (CONTENT.equals(mContent.mKey)) {
                String content = mContent.mValue;

                Matcher matcher = requestIdPattern.matcher(content);
                if (matcher.find()) {
                    logContents.setRequestId(matcher.group());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss:SSS");
                String[] contestArr = content.split(" ");
                Date parse = null;
                try {
                    parse = sdf.parse(contestArr[0] + contestArr[1]);
                } catch (ParseException e) {
                }
                logContents.setContent(content);
                logContents.setLogMills(parse == null ? null:parse.getTime());
            }

            if (HOSTNAME.equals(mContent.mKey)) {
                logContents.setHostName(mContent.mValue);
            }

            if (PACKID.equals(mContent.mKey)) {
                logContents.setPackId(mContent.mValue);
            }

            if (PACKMETA.equals(mContent.mKey)) {
                logContents.setPackMeta(mContent.mValue);
            }

        }

        if (getStackFeature()!=null && getStackFeature().size() > 1) {
            for (String feature : getStackFeature() ) {
                if ( logContents.getContent().contains(feature) ){
                    appendStack(logContents);
                }
            }
        }
        return logContents;
    }

    public void appendStack(LogContents logContents){

        QueryResult result = null;

        try {
            result = logContextQuery(logContents.getPackId(), logContents.getPackMeta(), 0, 100);
        } catch (Exception e) {
        }
        if (result == null || result.getLogs() == null || result.getLogs().size() < 1) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(logContents.getContent());

        for (LogContents log : result.getLogs()) {
            if ( isStack( log.getContent() ))
            sb.append(log.getContent() + "\r\n");
        }

        logContents.setContent(sb.toString());

    }

    private boolean isStack(String content){
        return content.contains("at");
    }

}
