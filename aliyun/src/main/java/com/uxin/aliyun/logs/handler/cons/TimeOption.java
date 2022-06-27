package com.uxin.aliyun.logs.handler.cons;

import com.sun.tools.javac.util.Pair;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum TimeOption {
    TODAY(1,"今天0时至今"),
    YESDAY(2,"昨天0时至昨天24时"),
    WEEK(3,"一周前0时至今"),
    CONSUM(0,"自定义");

    private Integer optionIndex;

    private String optionName;

    public Integer getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(Integer optionIndex) {
        this.optionIndex = optionIndex;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    TimeOption(Integer optionIndex, String optionName) {
        this.optionIndex = optionIndex;
        this.optionName = optionName;
    }

    public static void print() {
        EnumSet<TimeOption> es = EnumSet.allOf(TimeOption.class);
        for (TimeOption option : es) {
            System.out.print(option.getOptionIndex() + " )");
            System.out.println(option.getOptionName());
        }
    }

    private static Map<Integer,TimeOption> lookUp = new HashMap();

    static {
        EnumSet<TimeOption> es = EnumSet.allOf(TimeOption.class);
        for (TimeOption option : es) {
            lookUp.put(option.getOptionIndex(),option);
        }
    }



    public static Pair<Integer,Integer> get(Integer index) {

        long startMills = 0L , endMills = 0L;
        switch (lookUp.get(index)) {
            case TODAY:
                startMills = new DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
                endMills = System.currentTimeMillis();
                break;
            case YESDAY:
                startMills = new DateTime().minusDays(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
                endMills = new DateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
            case WEEK:
                startMills = new DateTime().minusDays(7).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
                endMills = System.currentTimeMillis();
            default:
                Date startDate = null;
                while ( startDate == null) {
                    System.out.println("输入开始时间 (yyyy-MM-dd HH:mm:ss): ");
                    Scanner scanner = new Scanner(System.in);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        startDate = sdf.parse(scanner.next());
                    } catch (ParseException e) {
                        System.out.println("输入时间不正确");
                    }
                }
                Date endDate = null;
                while ( endDate == null) {
                    System.out.println("输入结束时间 (yyyy-MM-dd HH:mm:ss): ");
                    Scanner scanner = new Scanner(System.in);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        endDate = sdf.parse(scanner.next());
                    } catch (ParseException e) {
                        System.out.println("输入时间不正确");
                    }
                }
                startMills = startDate.getTime();
                endMills = endDate.getTime();
                break;
        }

        return new Pair(Long.valueOf(startMills / 1000L).intValue() ,Long.valueOf(endMills / 1000).intValue() );
    }
}
