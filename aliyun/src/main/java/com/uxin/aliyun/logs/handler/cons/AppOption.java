package com.uxin.aliyun.logs.handler.cons;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AppOption {


    KILA(1,"克拉克拉 Kilakila"),
    MANB(2,"漫播 Manbo"),
    SERV(3,"Service"),
    All(0,"以上所有");

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

    AppOption(Integer optionIndex, String optionName) {
        this.optionIndex = optionIndex;
        this.optionName = optionName;
    }

    public static void print() {
        EnumSet<AppOption> es = EnumSet.allOf(AppOption.class);
        for (AppOption option : es) {
            System.out.print(option.getOptionIndex() + " )");
            System.out.println(option.getOptionName());
        }
    }

    private static Map<Integer,AppOption> lookUp = new HashMap();

    static {
        EnumSet<AppOption> es = EnumSet.allOf(AppOption.class);
        for (AppOption option : es) {
            lookUp.put(option.getOptionIndex(),option);
        }
    }



    public static AppOption get(Integer index) {
        return lookUp.get(index);
    }

}
