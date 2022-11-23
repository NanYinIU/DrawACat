package cat.test;

import com.uxin.zb.biz.commons.model.ThirdChannelType;

/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2022-01-05
 */
public class caseTest {

    private static boolean caseTest(ThirdChannelType thirdChannelType){
        switch (thirdChannelType) {
            case HONGDOU:
                System.out.println(1);
            case MANBO:
                System.out.println(2);
            case KLIVE:
                System.out.println(3);
            default:
                System.out.println("default");
        }
        return true;
    }

    public static void main(String[] args) {
        caseTest(ThirdChannelType.TALKER);
    }
}
