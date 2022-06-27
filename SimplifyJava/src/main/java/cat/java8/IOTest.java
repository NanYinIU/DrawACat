package cat.java8;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;

/**
 * @author nanyin
 * @class IOTest.java
 * @description TODO
 * @create 19:57 2020-04-05
 */
public class IOTest {
    public static void main(String[] args) {

    }

    @Test
    public void inputStreamTest() {
        String filePath = "/Users/gaoguoxing/Work/temp/a.txt";
        File file1 = new File(filePath);
        File file2 = new File(filePath);
        System.out.println(file1 == file2);
        System.out.println(file1.equals(file2));
        try (InputStream in = new FileInputStream(filePath); OutputStream out = new FileOutputStream(filePath)) {
            // 获取文件IO流
            byte[] content = new byte[100];
            int length = 0;
            // 没有返回 -1
            while ((length = in.read(content)) > 0) {
                out.write(content, 0, length);
            }
        } catch (IOException e) {

        }
    }

    public void outputStreamTest() {
        String content = "this is my content";
        try (OutputStream out = new FileOutputStream("copy.txt")) {
            out.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readerTest() {
        String filePath = "/Users/gaoguoxing/Work/temp/attachment/20200403/65bf6a2b0dbf3049dc08e800c2ac617385bb.xml";
        String s;
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writerTest() {
        String content = "this is my content";
        try (FileWriter out = new FileWriter("copy.txt");) {
            out.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileChannelTest() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 看一下初始时4个核心变量的值
        System.out.println("limit:" + byteBuffer.limit()); // 1024
        System.out.println("position:" + byteBuffer.position()); // 0
        System.out.println("capacity:" + byteBuffer.capacity()); // 1024
        System.out.println("mark:" + byteBuffer.mark());
        // mark:java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024]

        byteBuffer.put("hello world".getBytes());
        // 调用mark方法会返回this也就会输出当前buffer
        System.out.println("mark:" + byteBuffer.mark());
        // mark:java.nio.HeapByteBuffer[pos=11 lim=1024 cap=1024]

        // 切换成读模式
        byteBuffer.flip();
        System.out.println("mark:" + byteBuffer.mark());
        // mark:java.nio.HeapByteBuffer[pos=0 lim=11 cap=1024]
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));

        // 清空，再次可以向buffer中写数据
        byteBuffer.clear();
        byteBuffer.put("HELLO WORLD".getBytes());
        // mark:java.nio.HeapByteBuffer[pos=11 lim=1024 cap=1024]
        System.out.println("mark:" + byteBuffer.mark());

        // 切换成读模式
        byteBuffer.flip();
        // mark:java.nio.HeapByteBuffer[pos=0 lim=11 cap=1024]
        System.out.println("mark:" + byteBuffer.mark());
        bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));

        // 重复读
        System.out.println("mark:" + byteBuffer.mark());
        // mark:java.nio.HeapByteBuffer[pos=11 lim=11 cap=1024]
        byteBuffer.rewind();
        // mark:java.nio.HeapByteBuffer[pos=0 lim=11 cap=1024]
        System.out.println("mark:" + byteBuffer.mark());
        bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));

        try {
            // 1. 使用randomAccessFile 创建channel
            RandomAccessFile randomAccessFile = new RandomAccessFile("a.txt", "rw");
            FileChannel in = randomAccessFile.getChannel();
            // 2. 使用静态方法创建
            FileChannel out = FileChannel.open(Paths.get("b.txt"), StandardOpenOption.WRITE);

            // 3. 通过FileInputStream 创建channel
            FileInputStream inputStream = new FileInputStream("xxx.txt");
            inputStream.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            while (in.read(readBuffer) != -1) {
                readBuffer.flip();
                out.write(readBuffer);
                readBuffer.clear();
            }

            // 使用通道的transferTo
            in.transferTo(0, in.size(), out);

            // 关闭
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void nioTest() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/gaoguoxing/a.txt", "rw");
                FileChannel in = randomAccessFile.getChannel();
                FileChannel out = FileChannel.open(Paths.get("/Users/gaoguoxing/b.txt"), StandardOpenOption.WRITE);) {
            ByteBuffer readBuffer = ByteBuffer.allocate(10);
            while (in.read(readBuffer) != -1) {
                readBuffer.flip();
                out.write(readBuffer);
                readBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jdbcTest() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 建立数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "admin");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try (Statement st = conn.createStatement(); ResultSet rt = st.executeQuery("select * from table ");) {
            while (rt.next()) {
                rt.getString("test");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInteger() {
        Integer i = 1;
        Integer j = 1;
        System.out.println(i == j);// true
        Integer k = 129;
        Integer f = 129;
        System.out.println(k == f);// false

        Short a1 = 2;
        Short a2 = 2;
        System.out.println(a1 == a2);
        Short.valueOf(a1);// true

        Short b1 = new Short((short) 2);
        Short b2 = new Short((short) 2);
        System.out.println(b1 == b2); // false

        IOTest ioTest = new IOTest();
        IOTest ioTest1 = new IOTest();

        ioTest = ioTest1;
        System.out.println(ioTest == ioTest1);
        // ioTest.clone();
    }

}
