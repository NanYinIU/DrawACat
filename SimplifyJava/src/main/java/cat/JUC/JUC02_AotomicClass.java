package cat.JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nanyin
 * @class JUC02_AotomicClass.java
 * @description TODO
 * @create 15:15 2020-05-07
 */
public class JUC02_AotomicClass {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(2);
//        AtomicReference
        integer.getAndIncrement();
    }
}
