package cn.xz.io;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.jboss.logging.Logger;

import java.util.ArrayList;

/**
 * @author xz
 * @ClassName NettyServerHandler
 * @Description
 * @date 2019/8/8 0008 19:24
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static ArrayList<Channel> channels = new ArrayList<>();

    Logger logger = Logger.getLogger(NettyServerHandler.class);

    @Override // 读取就绪
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        logger.info(ctx.channel().remoteAddress() + "收到消息" + s);
        // 广播到所有连接上服务端的客户端中
        for (Channel channel : channels) {
            if(channel != ctx.channel()) {
                channel.writeAndFlush(s);
            }
        }
    }

    @Override // 通道就绪/客户端连接
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().remoteAddress() + "已上线");
        channels.add(ctx.channel());
    }

    @Override // 通道未就绪/客户端未连接
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.error(ctx.channel().remoteAddress() + "已离线");
    }
}
