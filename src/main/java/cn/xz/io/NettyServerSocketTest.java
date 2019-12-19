package cn.xz.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xz
 * @ClassName NettyServerSocketTest
 * @Description Netty 服务端
 * @date 2019/8/7 0007 20:10
 **/
public class NettyServerSocketTest {

    private int port;

    public NettyServerSocketTest( int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        // 服务端需要创建两个线程组

        // boss线程组用于处理客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // work 线程组用于处理客户端读写
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        // Netty 启动器类
        ServerBootstrap sb = new ServerBootstrap().group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class) // 服务器通道
                .option(ChannelOption.SO_BACKLOG, 128)  // 当服务请求处理线程满时 临时存放的最大数量 默认50
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 启用心跳机制 建立TCP握手之后
                .childHandler(new ChannelInitializer<SocketChannel>() { // 初始化 并向服务端Channel中添加自己的业务处理。
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {

                        /**
                         * 通道链中添加处理 感觉类似拦截器链 请求进来依次处理....
                         *  请求 -- XXX --- XXX --- XXX ---
                         *
                          */

                        channel.pipeline()
                                .addLast("decoder",new StringDecoder()) // 解码器 将客户端发来的二进制数据解码为String
                                .addLast("encoder",new StringEncoder()) // 编码器 将服务端发送的String 编码为二进制传输
                                .addLast(new NettyServerHandler()); // 自定义处理类 做自己的业务处理
                    }
                });

        ChannelFuture sync = sb.bind(port).sync();// 绑定一个端口，注意bind方法是异步的，但是sync是同步阻塞的。
        System.out.println("服务端已启动...");

        sync.channel().closeFuture().sync();

        // 关闭线程组
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
    public static boolean initKey = false;

    public static void main(String[] args) throws InterruptedException {

        //new NettyServerSocketTest(9999).run();
        // JMM 内存可见性面试题
           /* new Thread(() -> {
                while(!initKey){
                    int a = 0;
                    System.out.println(0); // 内部有使用sync 会重新到主内存中获取数据
                }
                System.out.println("success");
            }).start();

            Thread.sleep(100);

            new Thread(() -> {
                initKey = true;
                System.out.println("init");
            }).start();*/



    }

}
