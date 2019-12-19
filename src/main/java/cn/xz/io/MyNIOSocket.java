package cn.xz.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author xz
 * @ClassName MyNIOSocket
 * @Description NIO 网络编程客户端
 * @date 2019/7/30 0030 23:25
 **/
public class MyNIOSocket {
    public static void main(String[] args) throws IOException {
        // 创建管道
        SocketChannel channel = SocketChannel.open();

        // 设置为非阻塞模式
        channel.configureBlocking(false);

        // 连接服务端
        if (!channel.connect(new InetSocketAddress("127.0.0.1",8080))){
            System.out.println("连接失败");
            // 如果没有连接上则进行重试 NIO特点 非阻塞。
            while (!channel.finishConnect()) {
                System.out.println("重试失败");
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("夏周".getBytes());
        // 倒转数据指针
        buffer.flip();
        channel.write(buffer);

        System.in.read();
    }
}
