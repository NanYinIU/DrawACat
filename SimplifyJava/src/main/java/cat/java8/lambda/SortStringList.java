package cat.java8.lambda;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * day2:
 *
 * 使用Java8Lambda前后的对字符串的排序方法的实现区别：
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-18
 */
public class SortStringList {
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    /**
     * 在Java8 Lambda之前，如果需要对list中的字符串进行排序，需要使用到 Collections.sort 方法，并且创建一个 Comparator 匿名类。
     * 在匿名类中实现compare方法。
     * @param names
     */
    private void beforeLambdaSort(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * 在Java8 lambda之后，则可以使用 .sort方法，使用更简洁的lambda表达式代替原来的匿名类。
     * 从此变得更简单，并且可读性更强。
     * @param names
     */
    private void afterLambdaSort(List<String> names){
         names.sort((o1, o2) -> o1.compareTo(o2));
         names.sort(String::compareTo);
    }


    private int cala(MyFunctionalInterface myFunctionalInterface){
        return myFunctionalInterface.add(2,4);
    }

    public void client(){
        cala(((a1, a2) -> a1+a2+10));
    }

}
