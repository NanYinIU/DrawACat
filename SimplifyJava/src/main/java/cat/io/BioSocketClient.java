package cat.io;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 简单实现IO的客户端，更简单的方式是使用自带的 nc工具，如 <code> nc 192.168.2.2:8080 </code> 进行连接。
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-05
 */
public class BioSocketClient {
    public static void main(String[] args) {
        //获取用户输入的数据
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数据:");
        String inputData = input.nextLine();
        try (
                Socket sc = new Socket("127.0.0.1",8082);
                OutputStream os = sc.getOutputStream();
                BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(os))
        ) {
             writer.write(inputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开启一个Socket端口
    }
}
