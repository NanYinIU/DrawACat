package cat.test;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2021-08-26
 */
public class UxinTest {

    @Test
    public void test1(){

        DateTime dataTime = new DateTime(1634140798000L);
        dataTime = dataTime.plusDays(3*30);
        System.out.println(dataTime.toDate().getTime());
    }
}
