package cat.java8.interfaces;

import org.junit.Test;

/**
 * 测试客户端
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public class Client {


    @Test
    public void testMyInterface() {
        MyInterface myInterface = new MyInterface() {
            @Override
            public int add(int a1, int a2) {
                return a1 + a2;
            }
        };
        System.out.println(myInterface.add(1, 2));
        System.out.println(myInterface.sub(3,1));
    }
}
