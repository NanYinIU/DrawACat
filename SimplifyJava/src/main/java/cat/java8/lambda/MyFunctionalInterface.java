package cat.java8.lambda;

/**
 *
 *  day2
 *
 *  什么样的接口（创建匿名类）能够使用Lambda表达式? Lambda表达式如何进行对接口中给定类型的匹配？
 *  可以去到Comparator接口中寻找原因：
 *
 *  1. 首先需要满足接口中只有一个接口方法，在java8中可以在接口中定义default方法后，所以可以在一个接口中拥有一个普通的方法和若干个default方法
 *  2. 在上面的基础上面，还需要在接口上方使用注解 `@FunctionalInterface` 定义此接口为函数式接口
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-18
 */
@FunctionalInterface
public interface MyFunctionalInterface {

    int add(int a1,int a2);

    default int sub(int a1,int a2){
        return a1 - a2;
    };

}
