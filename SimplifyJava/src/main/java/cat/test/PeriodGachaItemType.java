package cat.test;

import com.uxin.zb.gacha.model.GachaItemType;
import com.uxin.zb.goods.service.model.GiftTabId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2022-12-06
 */
public enum PeriodGachaItemType {
    BARRAGE_BUBBLE(GachaItemType.BARRAGE_BUBBLE, GiftTabId.BACKPACK_BUBBLE),
    MOUNT_PROP(GachaItemType.MOUNT_PROP, GiftTabId.MOUNT_PROP),
    AVATAR_FRAME(GachaItemType.AVATAR_FRAME, GiftTabId.BACKPACK_AVATAR_FRAME);

    private static List<GachaItemType> defaultList = new ArrayList<>();
    private static Map<GiftTabId, GachaItemType> defaultMap = new HashMap<>();

    static {
        init();
        initMap();
    }



    private static void init() {
        defaultList.add(BARRAGE_BUBBLE.getType());
        defaultList.add(MOUNT_PROP.getType());
        defaultList.add(AVATAR_FRAME.getType());
    }

    private static void initMap() {
        defaultMap.put(BARRAGE_BUBBLE.getTabId(), BARRAGE_BUBBLE.getType());
        defaultMap.put(MOUNT_PROP.getTabId(), MOUNT_PROP.getType());
        defaultMap.put(AVATAR_FRAME.getTabId(), AVATAR_FRAME.getType());
    }

    public static List<GachaItemType> getDefaultList() {
        return defaultList;
    }

    public static GachaItemType getTypeByTabId(GiftTabId tabId){
        return defaultMap.get(tabId);
    }

    private GachaItemType type;
    private GiftTabId tabId;

    PeriodGachaItemType(GachaItemType type, GiftTabId tabId) {
        this.tabId = tabId;
        this.type = type;
    }

    public GachaItemType getType() {
        return type;
    }

    public GiftTabId getTabId() {
        return tabId;
    }
}
