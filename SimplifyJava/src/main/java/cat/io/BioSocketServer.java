package cat.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * IO实现Socket连接，在内核内需要实现几步
 *
 * 1. 使用socket()函数获取文件描述符(file descriptor) -- <code>socket(int domain, int type, int protocol);</code>
 * 2. 使用bind()函数将文件描述符和地址端口进行绑定
 * 3. 使用listen()函数进行端口的监听  <code> listen(int socket, int backlog); </code>
 * 4. 使用accept()函数进行客户端的获取
 *
 * > 使用man命令查看相关内核函数
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-05
 */
public class BioSocketServer {
    public static void main(String[] args) {
        System.out.println("等待客户端连接数据.....");
        try{
            ServerSocket server = new ServerSocket(8082);
            // 阻塞的获取客户端状态
            Socket client = server.accept();
           System.out.println("准备服务端，客户端端口号为:"+client.getPort());
           while(true){

               // 1. 取新线程读取客户端数据
//               new Thread(new Runnable() {
//                   Socket client ;
//
//                   public Runnable setClient(Socket client) {
//                       this.client = client;
//                       return this;
//                   }
//
//                   @Override
//                   public void run() {
//                       try{
//                           InputStream inputStream = client.getInputStream();
//                           // 阻塞的进行read
//                           BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                           while(true){
//                               System.out.println("准备从客户端获取数据。");
//                               String s = reader.readLine();
//                               System.out.println("从客户端获取的数据:" + s);
//                           }
//                       }catch (Exception e){
//                           e.printStackTrace();
//                       }
//                   }
//               }.setClient(client)).start();

               // 2. 直接读取客户端数据，会阻塞的读取，无法再次连接其他客户端。
               InputStream inputStream = client.getInputStream();
               // 阻塞的进行read
               BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
               while(true) {
                   Thread.sleep(1000);
                   System.out.println("准备从客户端获取数据。");
                   String s = reader.readLine();
                   System.out.println("从客户端获取的数据:" + s);
               }
           }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
