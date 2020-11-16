package cat.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListTest {

    public static void main(String[] args) {
        String[] objectArray = new String[1];
        objectArray[0] = "I don't fit in";

//        List<Long> ol = new ArrayList<Long>(); // Incompatible types
//        ol.add("I don't fit in");
        List<String> list = new ArrayList<>();
        list = Arrays.stream(objectArray).flatMap(s -> Arrays.stream(s.split(" "))).collect(Collectors.toList());
        list.forEach(System.out::println);
        String s = "hello,world";


    }
}
