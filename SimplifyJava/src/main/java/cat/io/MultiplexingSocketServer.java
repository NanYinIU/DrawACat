package cat.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Java 单线程实现多路复用
 *
 * 和NIO类似服务端和接收到的客户端轮询都是非阻塞的；
 * 并且多路复用的核心就是利用内核自带的select函数，只有当注册的客户端注册过消息后，自动通知可读取。
 * 以优化NIO中需要用户来每次需要对所有的链接的Client都进行轮询（用户空间进行），优化为内核为用户提供轮询（内核空间进行）。
 *
 * 1. 使用socket()函数获取文件描述符(file descriptor) -- <code>socket(int domain, int type, int protocol);</code>
 * 2. 使用bind()函数将文件描述符和地址端口进行绑定
 * 3. 使用listen()函数进行端口的监听  <code> listen(int socket, int backlog); </code>
 * 4. 使用select(fds)函数将文件描述符传给内核，让内核去处理，哪些fds有数据可进行accept或可进行read操作。
 * 5. 如果能够select>0,说明具有连接、可读的客户端，如果使用accept()函数进行客户端的获取、或使用buffer进行数据的读取
 *
 *
 * @see NioSocketServer
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2020-10-07
 */
public class MultiplexingSocketServer {
    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel;
        Selector selector ;
        try {
            // 对应内核中的 socket
            serverSocketChannel = ServerSocketChannel.open();
            // 对应内核中的 bind
            serverSocketChannel.bind(new InetSocketAddress(8090));
            // 服务端设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            System.out.println("=======================");
            System.out.println("服务端8090端口准备完成，等待客户端连接...");
            System.out.println("=======================");

            // 将select注册到server中
            selector = Selector.open();
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocketChannel keyServerChannel ;
            SocketChannel client = null;
            while(true){
                if(selector.select(0) > 0){
                    // 如果有连接进来了 ，获取所有的连接
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();
                    // 循环所有连接
                    while(keys.hasNext()){
                        SelectionKey key = keys.next();
                        keys.remove();
                        // 如果需要进行连接
                        if(key.isAcceptable()){
                            // 和NIO相同，获取客户端
                            keyServerChannel = (ServerSocketChannel) key.channel();
                            client = keyServerChannel.accept();
                            // 将客户端设置为非阻塞状态
                            client.configureBlocking(false);
                            System.out.println("客户端"+ client.getRemoteAddress()+"连接成功...,将客户端添加至select中，状态为待读..");
                            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2056);
                            // 客户端连接，并注册到了selector上面，将bytebuffer注册进去，作为attachment，状态设置为read状态。
                            client.register(selector,SelectionKey.OP_READ,byteBuffer);
                            // 如果需要对客户端进行过读取
                        } else if(key.isReadable()){
                            // 通过channel 获取 客户端，用于读取
                            client = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            int read = client.read(buffer);
                            // 如果read>0，证明是有数据的，则将buffer翻转，进行相关读取，然后清空缓冲区。
                            if(read > 0){
                                buffer.flip();
                                byte[] bytes = new byte[buffer.limit()];
                                buffer.get(bytes);
                                System.out.println("客户端"+ client.getRemoteAddress()+"收到数据成功..读取数据为"+new String(bytes));
                                buffer.clear();
                            }
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
