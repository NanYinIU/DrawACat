package cat.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * day4
 * Optional是可以整洁的防止空指针异常，Option就是一个容器类，里面的内容可以为null，也可以为 not-null,
 * 以前返回null值的都可以使用Optional进行替代。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-23
 */
public class Optionals {

    @Test
    public void testSimpleOptional(){
        Optional<String> simpleOptional = Optional.of("test");
        System.out.println(simpleOptional.get());
        System.out.println(simpleOptional.isPresent());
        simpleOptional.ifPresent((s) -> {
            System.out.println("test2");
        });
    }

}
