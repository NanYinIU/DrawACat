package com.uxin.aliyun.logs.handler.cons;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TypeOption {


    INFO(1,"info"),
    ACCESS(2,"access"),
    ERROR(3,"error"),
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

    TypeOption(Integer optionIndex, String optionName) {
        this.optionIndex = optionIndex;
        this.optionName = optionName;
    }

    public static void print() {
        EnumSet<TypeOption> es = EnumSet.allOf(TypeOption.class);
        for (TypeOption option : es) {
            System.out.print(option.getOptionIndex() + " )");
            System.out.println(option.getOptionName());
        }
    }

    private static Map<Integer,TypeOption> lookUp = new HashMap();

    static {
        EnumSet<TypeOption> es = EnumSet.allOf(TypeOption.class);
        for (TypeOption option : es) {
            lookUp.put(option.getOptionIndex(),option);
        }
    }



    public static TypeOption get(Integer index) {
        return lookUp.get(index);
    }
}
