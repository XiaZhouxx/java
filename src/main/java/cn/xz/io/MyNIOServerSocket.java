package cn.xz.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xz
 * @ClassName TestNIOServerSocket
 * @Description NIO 网络编程
 * @date 2019/7/28 0028 22:11
 **/
public class MyNIOServerSocket {
    public static void main(String[] args) throws IOException {
        // 创建一个服务器管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 获取选择器
        Selector selector = Selector.open();
        // 注册服务端管道到选择器中 让选择器监听客户端事件 首先肯定是监听客户端连接
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        // 开始对客户端产生的事件进行一一处理
        while(true){
            // 为 0 代表没有客户连接 间隔 timeout 再次轮询
            if(selector.select(2000) == 0) {
                System.out.println("没有人连接我啊！");
                continue;
            }


            // 有客户端时 SelectionKey 就相当于连接的客户端
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()) {
                // 获取监听客户端的单个操作对象
                SelectionKey key = iterator.next();
                // 是否客户端连接
                if(key.isAcceptable()){
                    System.out.println("有连接了");
                    // 接收该连接
                    SocketChannel sc = serverSocketChannel.accept();
                    // 同样设置非阻塞
                    sc.configureBlocking(false);
                    // 将该客户端注册到选择器进行下个操作的绑定
                    sc.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));

                }

                // 是否读取事件
                if (key.isReadable()){
                    System.out.println("客户端发消息了");
                    // 获取管道
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 获取缓冲区 上方连接事件中存放的附件 也可以存放其他对象。
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    // 不等于-1 相当于管道中还有数据
                    while (channel.read(buffer) != -1){
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                        buffer.clear();
                    }
                    // 读取到-1 说明客户端已断开连接 需要关闭管道 不关闭导致后续重复使用！！！！
                    channel.close();
                }
                // 防止重复操作该key
                iterator.remove();
            }

        }

    }
}
