## IO、NIO、多路复用器

- application（应用） 与 输入输出工具之间的交互是依赖与内核（操作系统）的.

在机器启动后，首先运行的是操作系统，会为操作系统内核开辟一个内存空间用于运行内核相关内容，此为==内核空间==。然后才会在内核上启动其他应用程序，开辟其他应用的空间，用户可随意操作的成为==用户空间==。用户或应用程序无法直接修改内核或操作内核，只能根据内核给出的接口或中断来操作IO设备。

- 在IO、NIO、多路复用器都是需要操作系统内核先支持，Java程序再出现API。才能实现Java操作IO流。

### BIO(block)

BIO过程就如同名字一样,是一个阻塞的IO,**服务端通常为每一个客户端都建立一个独立的线程来通过调用accept()来监听客户端消息**.如果想处理多个客户端请求则服务端需要建立等同数量的线程来处理这些消息,这就是普遍的一请求一应答的模型.处理完成后返回应答给客户端后销毁线程,因为线程是一个昂贵的资源,这样重复的新建线程,销毁线程,很浪费处理器资源。

在处理链接数量少的情况下,BIO的效率还不错,并且主要逻辑模型清晰明了,代码简单.但是在上万（c10w）的链接的情况下,就会创建上万个线程，线程数量过大就会造成内核频繁发生上下文的切换，执行程序的效率就会下降。

BIO会发生四个步骤：

 * 使用socket()函数获取文件描述符(file descriptor)  <code>socket(int domain, int type, int protocol);</code>
 * 使用bind()函数将文件描述符和地址端口进行绑定
 * 使用listen()函数进行端口的监听  <code> listen(int socket, int backlog); </code>
 * 使用accept()函数进行客户端的获取

#### BIO问题

BIO建立服务端后，因为accept操作时阻塞的，并且也需要阻塞的进行读取客户端数据，所以接收客户端操作和读取客户端数据不能放在一个线程内执行，否则一直会是线性的操作，无法多个客户端连接读取数据。

如果再客户端非常多的情况下，系统会在线程上下文切换上花费非常多的时间，导致会效率下降。

### NIO（non-block）

就像上面说的一样，只有内核提供了NIO（non-block）特性之后Java才能通过API进行相关操作。NIO是面向缓冲区的、非阻塞的IO模型。通过Buffer、Channel等相关NIO包类进行实现。

-   在内核内通过select时标记为`non-blocking`来达到非阻塞的目的。可以使用`man 2 select`进行查看.
-   其他的内核函数都与上面的BIO相同。

#### NIO问题

NIO解决了BIO中阻塞的问题，没必要一个线程对应一个客户端了，减少了资源浪费。

但是NIO在连接客户端后，虽然是非阻塞的，但是用户需要每次遍历客户端（不管是否有数据到达），用来知道是否客户端发送了数据，如果发送了数据，则去读取数据。如果客户端非常多，但是发生数据的客户端非常少，就会产生非常多的CPU空转。

### 多路复用器


> 测试可以使用环境自带的nc进行测试，如 `nc 192.168.2.2:8090`。
> 示例代码在 src/main/java/cat/io 包下