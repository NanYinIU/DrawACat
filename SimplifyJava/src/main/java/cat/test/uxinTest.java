package cat.test;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-07-23
 */
public class uxinTest {
    @Test
    public void test01(){
        Long firstOpenPrice = 1000L;
        Long renewPrice = 980L;
        Long commissionProp = 7L;
        long value = new Double(Math.ceil((firstOpenPrice - renewPrice) * (commissionProp * 1.0 / 100))).longValue();
        long value1 = new Double(Math.round((firstOpenPrice - renewPrice) * (commissionProp * 1.0 / 100))).longValue();
        System.out.println(value);
        System.out.println(value1);
    }

    @Test
    public void test02(){
        Long time = 1634572798000L;
        DateTime dataTime = null;
        dataTime = new DateTime(time);
        DateTime plusDays = dataTime.plusDays(16 * 30);
        DateTime minusDays = dataTime.minusDays(30);
        System.out.println(minusDays.toDate().getTime());

        String s = buildWeekTimeByZoneWithOffset(-1);
        System.out.println(s);
    }

    public static String buildWeekTimeByZoneWithOffset(Integer offset) {
        DateTime now = DateTime.parse("2022-09-11").plusWeeks(-1);
        if (false) {
            now = DateTime.now().plusWeeks(-1).toDateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(ZoneId.SHORT_IDS.get("JST"))));
        }
        int year = now.getWeekyear();
        int week = now.getWeekOfWeekyear();
        return String.valueOf(year).concat(String.valueOf(week));
    }



}
