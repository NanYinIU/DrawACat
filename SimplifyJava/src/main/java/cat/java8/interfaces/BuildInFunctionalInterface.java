package cat.java8.interfaces;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * day3
 *
 * Java8提供了很多自带的函数式接口，一方面是原来的接口如Comparable、Runnable,通过添加了 @FunctionalInterface 来实现
 * 另一方面还提供了四种不同的函数式接口，分别为 Predicates Functions Suppliers Consumers
 *
 * # Predicates
 * Predicates 是一个参数的布尔值函数
 *
 * # Functions
 * Functions 是一个参数，并且有返回值的函数，默认可以将多个函数连接起来
 *
 * # Suppliers
 * Suppliers 是没有参数，但是会有给定返回值的函数
 *
 * # Consumers
 * Consumers 可以执行一个参数的函数，没有返回值
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-22
 */
public class BuildInFunctionalInterface {
    @Test
    public void testPredicates(){
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("word"));

        Predicate<Object> notNull = Objects::nonNull;
        System.out.println(notNull.test("0"));
    }

    @Test
    public void testFunction(){
        Function<String,Integer> outputInt = Integer::valueOf;
        Function<Integer,Integer> cala = s -> s+10;
        Integer apply = outputInt.apply("12");
        System.out.println(apply);
        Integer apply1 = outputInt.andThen(cala).apply("12");
        System.out.println(apply1);
    }

    @Test
    public void testSuppliers(){
        Supplier<String> toString = () -> "world";
        System.out.println(toString.get());

        Supplier<String> newString = String::new;

    }

    @Test
    public void testConsumers(){
        Consumer<Integer> consumer = System.out::print;
        int a = 2;
        consumer.accept(a);
    }


}
