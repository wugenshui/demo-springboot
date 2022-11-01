package com.github.wugenshui.socket.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author : chenbo
 * @date : 2022-10-31
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        System.out.println("客户端收到数据: " + msg.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //cause.printStackTrace();//捕捉异常信息
        System.out.println(cause.getMessage());
        ctx.close();//出现异常时关闭channel
    }
    // @Override
    // protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
    //     System.out.println("客户端收到数据: " + msg.toString(CharsetUtil.UTF_8));
    // }
}
