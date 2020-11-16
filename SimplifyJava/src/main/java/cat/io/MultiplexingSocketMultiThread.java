package cat.io;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 多线程多路复用主从模型，在单线程的基础上进行拓展，分为master、worker，master进行accept客户端，worker读取客户端数据
 *
 * 有点接近于netty中使用NIO的模型
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-08
 */
public class MultiplexingSocketMultiThread {

    public static void main(String[] args) {

        ServerSocketChannel server;
        Selector selector ;
        try {
            // 对应内核中的 socket
            server = ServerSocketChannel.open();
            // 对应内核中的 bind
            server.bind(new InetSocketAddress(8090));
            // 服务端设置为非阻塞
            server.configureBlocking(false);

            System.out.println("=======================");
            System.out.println("服务端8090端口准备完成，等待客户端连接...");
            System.out.println("=======================");

            // 将select注册到server中
            selector = Selector.open();
            SelectionKey selectionKey = server.register(selector, SelectionKey.OP_ACCEPT);

            // 在主线程里面做选择和注册客户端

            // 启动单独线程进行读写操作，同样可以设置多个线程进行读写。

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
