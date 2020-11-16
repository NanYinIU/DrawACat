package cat.java8;

/**
 * @author nanyin
 * @class ThreadLocalTest.java
 * @description TODO
 * @create 11:05 2020-04-03
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("测试");
    }
}
