package com.uxin.util;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Getter
@Setter
public class PageParam implements Serializable {

    private int pageNo = 1;

    private int pageSize = 100;

    private int beginTime = Long.valueOf(new DateTime().minusDays(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis() / 1000).intValue();

    private int endTime = Long.valueOf(new DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis() / 1000).intValue();

    private boolean resver = true;

    private boolean allLogs = true;

    private String query;

    private PageParam() {
    }


    public PageParam(Integer begin , Integer end , String query){
        beginTime = begin;
        endTime = end;
        this.query = query;
    }


    public int getOffset(){
        return (pageNo - 1) * pageSize;
    }


}
