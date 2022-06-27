package com.uxin.util;

import javafx.util.Pair;
import lombok.Getter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
public class ApiSuccessReq {


    private Pair<Integer,Integer> total;
    private Pair<Integer,Integer> failed;
    private Pair<Integer,Integer> high;

    private ApiSuccessReq() {
    }

    public ApiSuccessReq(Date date) {
        int start = Long.valueOf(new DateTime(date).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis() / 1000).intValue();
        int end = Long.valueOf(new DateTime(date).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999).getMillis() / 1000).intValue();
        int highStart = Long.valueOf(new DateTime(date).withHourOfDay(20).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis() / 1000).intValue();

        this.total = new Pair(start,end);
        this.failed = new Pair(start,end);
        this.high = new Pair(highStart,end);
    }

    public static List<ApiSuccessReq> rangeReq(){
        List<ApiSuccessReq> result = new ArrayList<>();
        Date init = new Date();
        for (int i = 7 ; i > 0 ; i--) {
            result.add(new ApiSuccessReq(new DateTime(init).minusDays(i).toDate()));
        }
        return result;
    }
}
