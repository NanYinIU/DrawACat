package cat.test;

import com.alibaba.fastjson.JSON;
import com.uxin.zb.biz.commons.model.ThirdChannelType;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 禁言列表枚举
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2022-12-28
 */
public enum BanPanelOptionsEnum {
    // one hour
    ONE_HOUR(1, "1小时", "1時間", "1 hour", 60 * 60 * 1000L),
    // one day
    ONE_DAY(2, "1天", "1日間", "1 day", 24 * 60 * 60 * 1000L),
    // seven days
    SEVEN_DAYS(3, "7天", "7日間", "7 days", 7 * 24 * 60 * 60 * 1000L),
    // fourteen days
    FOURTEEN_DAYS(4, "14天", "14日間", "14 days", 14 * 24 * 60 * 60 * 1000L),
    // forever
    FOREVER(5, "永久", "永久", "permanent", -1L);

    private static final Map<Integer, BanPanelOptionsEnum> lookup = new HashMap<>();

    private Integer id;

    private String desc;
    private String descForJp;
    private String descForEn;
    private Long distanceTime;

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescForJp() {
        return descForJp;
    }

    public String getDescForEn() {
        return descForEn;
    }

    public Long getDistanceTime() {
        return distanceTime;
    }

    BanPanelOptionsEnum(Integer id, String desc, String descForJp, String descForEn, Long distanceTime) {
        this.id = id;
        this.desc = desc;
        this.descForJp = descForJp;
        this.descForEn = descForEn;
        this.distanceTime = distanceTime;
    }

    static {
        for (BanPanelOptionsEnum banPanelOptionsEnum : EnumSet.allOf(BanPanelOptionsEnum.class)) {
            lookup.put(banPanelOptionsEnum.getId(), banPanelOptionsEnum);
        }
    }

    public static BanPanelOptionsEnum getBanPanelOptionsEnumById(Integer id) {
        return lookup.get(id);
    }

    public static List<BanPanelOption> getBanPanelOptionsJsonFromSerializeMap(ThirdChannelType appId) {
        BanPanelOptionsEnum[] values = values();
        System.out.println(JSON.toJSONString(values));
        List<BanPanelOption> banPanelOptions = new ArrayList<>();
        for (BanPanelOptionsEnum banPanelOptionsEnum : values) {
            BanPanelOption banPanelOption = new BanPanelOption();
            banPanelOption.setSlienceTypeId(banPanelOptionsEnum.getId());
            banPanelOption.setSlienceTypeName(banPanelOptionsEnum.getDesc());
            if(ThirdChannelType.KLIVE.equals(appId)){
                banPanelOption.setSlienceTypeName(banPanelOptionsEnum.getDescForJp());
            }
//            if(ThirdChannelType.MAN)
            banPanelOptions.add(banPanelOption);
        }
        return banPanelOptions;
    }

    public List<BanPanelOptionsEnum> from(ThirdChannelType appId) {
        List<BanPanelOptionsEnum> list = new ArrayList<>();
        for (BanPanelOptionsEnum v : values()) {
            System.out.println(v.getDesc());
            if (ThirdChannelType.KLIVE.equals(appId)) {
                v.desc = v.descForJp;
            }
            list.add(v);
        }
        return list;
    }

}
