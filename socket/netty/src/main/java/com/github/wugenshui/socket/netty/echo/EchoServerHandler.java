package com.github.wugenshui.socket.netty.echo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : chenbo
 * @date : 2022-10-31
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("服务器收到 " + ctx.channel().localAddress() + " 消息: " + msg.toString());
        // 将接收到的消息写给发送者，即客户端，而不冲刷出站消息
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 打印异常信息
        cause.printStackTrace();
        // 出现异常时关闭channel
        ctx.close();
    }
}
