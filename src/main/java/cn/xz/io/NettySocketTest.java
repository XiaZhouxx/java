package cn.xz.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author xz
 * @ClassName NettySocketTest
 * @Description Netty 客户端
 * @date 2019/8/7 0007 20:10
 **/
public class NettySocketTest {

    private String host;
    private int port;

    public NettySocketTest(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        // 客户端只需要一个线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap handler = new Bootstrap().group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast("decoder",new StringDecoder())
                                .addLast("encoder",new StringEncoder())
                                .addLast(new NettyClientHandler());
                    }
                });
        // 连接服务端
        ChannelFuture sync = handler.connect(host, port).sync();
        Channel c = sync.channel();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            c.writeAndFlush(sc.nextLine());
        }

        sync.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new NettySocketTest("127.0.0.1",9999).run();

    }
}
