package cat.java8.strams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java.util.Stream 代表着对一序列内容操作、执行的过程,Stream既可以出现在中间，也可以出现在结尾。
 * 如果出现在中间,则是返回Stream,以便能够使用链状的结构连起来，如果出现在结尾，则能够返回一个确定的类型数据。
 * Streams都是通过一个源进行创建，如list、set等等。能够串行化、并行化进行。
 *
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-11-23
 */
public class SimpleStreams {

    public List<String> initSimpleStringList(){
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("e");
        stringList.add("c");
        stringList.add("d");
        stringList.add("f");
        stringList.add("b");
        stringList.add("g");
        return stringList;
    }

    @Test
    public void testSimpleStreams(){
        SimpleStreams simpleStreams = new SimpleStreams();
        List<String> strings = simpleStreams.initSimpleStringList();
        strings
                .stream()
                .filter(s -> !"a".equals(s))
                .sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        boolean a = strings.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(a);

        long count = strings.stream().count();
        strings.stream().reduce((s, s2) -> s + "#"+s2).ifPresent(System.out::println);


    }
}
