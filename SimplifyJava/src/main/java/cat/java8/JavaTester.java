package cat.java8;


import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JavaTester {

    @Test
    public void functionTest() {
        // R apply(T t);
        // Function<T, R>
        // 定义默认方法实现
//        Function<String,Integer> fun = (s) -> Integer.parseInt(s+"1");

//        Function<String, Integer> fun = Integer::parseInt;
//
        List<Integer> list = Arrays.asList(1, 2, 3 ,4 , 4);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//        list.forEach(System.out::println);
//        list.forEach((s) -> System.out.println(s));
//
//        // map 实例
//        for (Integer integer : list) {
//            int order = integer + 2;
//            System.out.println(order);
//        }
//
//        list.stream().map((a) -> a + 2).forEach(System.out::println);
//
//        // map collect
//
//        List<Integer> newList = new ArrayList<>();
//        for (Integer integer : list) {
//            int order = integer + 2;
//            int result = order / integer;
//            newList.add(result);
//        }
//        for (Integer integer : newList) {
//            System.out.println(integer);
//        }

//        newList = list.stream().map((a) -> (a + 2)/a).collect(Collectors.toList());
//        newList.forEach(System.out::println);

//        List<Integer> list_1 = new ArrayList<>();
//        for (Integer integer : list) {
//            if(integer != 3){
//                list_1.add(integer);
//            }
//        }
//
//
//        // filter
//        List<Integer> list_2 = list.stream().filter((a) -> a!=3).collect(Collectors.toList());
//        list_2.forEach(System.out::println);

        //distinct

        List<Integer> list_3 = list.stream().map(a -> a * a).distinct().collect(Collectors.toList());
        list_3.forEach(System.out::println);

        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("最大值："+intSummaryStatistics.getMax());
        System.out.println("最小值："+intSummaryStatistics.getMin());
        System.out.println("平均值："+intSummaryStatistics.getAverage());
        System.out.println("数量："+intSummaryStatistics.getCount());

        Optional<Integer> min = list.stream().min(Integer::compareTo);

        System.out.println(min.get());

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        // 使用特定处理函数
        List<String> s = new ArrayList<>();
        s.add("hello ");
        s.add("world ");
        s.add("!! ");

        List<String> s_1 = s.stream()
                .filter(a -> a.startsWith("h") || a.startsWith("w"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        s_1.forEach(System.out::println);



        JavaTester javaTester = new JavaTester();
////        List<Integer> resList = javaTester.functionMethods(list,(a)->a.stream().filter((b)-> b!=2).collect(Collectors.toList()));
        List<Integer> resList = javaTester.functionMethods2(list, a -> {
            a.add(2);
            return a;
        });
//        resList.forEach(System.out::println);
//        // 执行实现的方法
////        Integer result = fun.apply("2");
////        System.out.println(result);
//
//        MathOperate addMethod = (a, b) -> a + b;
//        System.out.println(addMethod.MathOperate(2, 3));
//
//        MathOperate subMethod = (a, b) -> a - b;
//        System.out.println(subMethod.MathOperate(2, 3));
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("新建线程。。");
//            }
//        }).start();
//
//        new Thread(() -> System.out.println("使用Lambda新建线程。。")).start();


        Consumer<String> consumer1 = c1 -> System.out.println("0-元素："+c1.split(" ")[0]);
        Consumer<String> consumer2 = c2 -> System.out.println("1-Upper：" + c2.split(" ")[1].toUpperCase());

        String[] strings = {"hello world"};
        Arrays.stream(strings).forEach((t) -> consumer1.andThen(consumer2).accept(t));

        Predicate predicate = (a) -> (int)a - 2 > 0;
        boolean test = predicate.test(5);
        System.out.println(test);
    }

    List<Integer> functionMethods(List<Integer> a, Function<List<Integer>, List<Integer>> function) {
        return function.apply(a);
    }

    List<Integer> functionMethods2(List<Integer> a, Function<List<Integer>, List<Integer>> function) {
        a = a.stream()
                .filter(demoFilter(2))
                .collect(Collectors.toList());
        return function.apply(a);
    }

    void NumberFilter(Integer t, Predicate<Integer> predicate) {
        predicate.test(t);
    }


    Predicate<Integer> demoFilter(Integer number) {
        return (a) -> !a.equals(number);
    }


//    Predicate<Integer> mixFilter(Integer num,List<Integer> spec){
//        spec.stream().filter((a)->!a.equals(num)).collect(Collectors.toList());
//
//        return
//    }

    interface MathOperate {
        int MathOperate(int a, int b);
    }

}
