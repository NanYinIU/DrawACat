package cat.java8.interfaces;

/**
 *
 * What: Java8中可以使用default关键字在接口中声明一个实现方法,并能够在客户端中直接像实现方法一样使用。
 *
 * why：原来的接口中没有default方法，在实现接口的时候，需要重写接口中的所有方法。
 *
 * Result: 在实现了default方法函数后，在 new 实现匿名实现类时，代码更加简洁，default甚至不需要重写。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-17
 */
public interface MyInterface {
    int add(int a1,int a2);

    default int sub(int a1,int a2){
        return a1 - a2;
    };

}
