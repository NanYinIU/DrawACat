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
 * Java 实现多路复用
 *
 * 和NIO类似服务端和接收到的客户端轮询都是非阻塞的；
 * 并且多路复用的核心就是利用内核自带的select函数，只有当注册的客户端注册过消息后，自动通知可读取。
 * 以优化NIO中需要用户来每次需要对所有的链接的Client都进行轮询（用户空间进行），优化为内核为用户提供轮询（内核空间进行）。
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
                    // 有客户端连接进来了
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();
                    while(keys.hasNext()){
                        SelectionKey key = keys.next();
                        keys.remove();
                        if(key.isAcceptable()){
                            // 进行client的消息接收accept
                            keyServerChannel = (ServerSocketChannel) key.channel();
                            client = keyServerChannel.accept();
                            client.configureBlocking(false);
                            System.out.println("客户端"+ client.getRemoteAddress()+"连接成功...,将客户端添加至select中，状态为待读..");
                            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2056);
                            client.register(selector,SelectionKey.OP_READ,byteBuffer);
                            // 客户端连接，并注册到了selector上面
                        } else if(key.isReadable()){
                            // 进行client的消息处理，获取所有客户端，然后进行数据读取
                            client = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            int read = client.read(buffer);
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
