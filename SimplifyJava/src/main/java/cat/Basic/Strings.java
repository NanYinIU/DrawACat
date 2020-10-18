package cat.Basic;

/**
 * 查看String、StringBuffer、StringBuilder的区别
 * 总的来说，如果使用拼接推荐后两者。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-13
 */
public class Strings {
    public static void main(String[] args) {
        String s = "hello ";
        StringBuilder stringBuilder = new StringBuilder("hello ");
        StringBuffer stringBuffer = new StringBuffer("hello ");
        // 拼接字符串,每次都生成字符串，生成了hello、world、hello world三个字符串（前提是字符串常量池中没有这三个字符串信息）
        s += "world";
        // 使用类似ArrayList的char数组扩容的机制
        stringBuilder.append("world");
        // 线程安全的stringBuilder
        stringBuffer.append("world");
    }
}
