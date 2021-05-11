package cat.test;

import org.junit.Test;

/**
 *
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2021-05-08
 */
public class RegexTest {

    @Test
    public void test1(){
        String regStr = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";
        System.out.println("12Wnn".matches(regStr));
    }

    @Test
    public void test2(){
        String regStr = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).*${0,20}";
        System.out.println("12Wn@".matches(regStr));
    }

    @Test
    public void test3(){
        String regStr = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{0,20}$";
        System.out.println("12wv".matches(regStr));
    }

    @Test
    public void test5(){
        String regStr = "^[a-zA-Z0-9]+${0,16}";
        System.out.println("1ABCDEFGH".matches(regStr));
        System.out.println("1abcdefgh".matches(regStr));
        System.out.println("abcdefgh".matches(regStr));
        System.out.println("ABCDEFGH".matches(regStr));
        System.out.println("ABCDEFGH2".matches(regStr));
        System.out.println("abcdefgh2".matches(regStr));
        System.out.println("1ABCDEFGH2".matches(regStr));
        System.out.println("1abcdefgh2".matches(regStr));
        System.out.println("1Abcdefgh2".matches(regStr));
    }

    @Test
    public void test4(){
        String regStr = "(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        System.out.println("1ABCDEFGH".matches(regStr));
        System.out.println("1abcdefgh".matches(regStr));
        System.out.println("abcdefgh".matches(regStr));
        System.out.println("ABCDEFGH".matches(regStr));
        System.out.println("ABCDEFGH2".matches(regStr));
        System.out.println("abcdefgh2".matches(regStr));
        System.out.println("1ABCDEFGH2".matches(regStr));
        System.out.println("1abcdefgh2".matches(regStr));
        System.out.println("1Abcdefgh2".matches(regStr));
    }
}
