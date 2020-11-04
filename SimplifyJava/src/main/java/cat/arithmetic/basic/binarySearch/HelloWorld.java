package cat.arithmetic.basic.binarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-03
 */
public class HelloWorld {
    public static void main(String[] args) {
        List<String> newList = new ArrayList<>();

        newList = newList.stream().map((a) -> (a+",")).collect(Collectors.toList());
        newList.forEach(System.out::println);
    }
}
