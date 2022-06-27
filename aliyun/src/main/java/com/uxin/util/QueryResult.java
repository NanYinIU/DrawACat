package com.uxin.util;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class QueryResult {


    private long totalCount = 0;


    private List<LogContents> logs = new ArrayList();


    public static QueryResult merge(QueryResult ... results){
        long resultCount = 0;
        List<LogContents> mergeLog = new ArrayList();

        for (QueryResult result : results) {
            mergeLog.addAll(result.getLogs());
            resultCount += result.getTotalCount();
        }
        mergeLog.sort(Comparator.comparingLong(LogContents::getLogMills));

        QueryResult total = new QueryResult();
        total.totalCount = resultCount;
        total.logs = mergeLog;
        return total;
    }


    public void print(){

        if (logs == null || logs.size() < 1 ) {
            System.out.println("查询结果为空");
        }

        List<LogContents> list = logs.stream().sorted(Comparator.comparingLong(LogContents::getLogMills)).collect(Collectors.toList());

        for (LogContents content : list) {
            StringBuilder sb = new StringBuilder();
            sb.append(content.getHostIp());
            sb.append(" ");
            sb.append(content.getContent());
            System.out.println(sb);
        }

    }


}
