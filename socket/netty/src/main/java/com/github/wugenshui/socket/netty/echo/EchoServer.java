package com.github.wugenshui.socket.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author : chenbo
 * @date : 2022-10-31
 */
public class EchoServer {
    public static void main(String[] args) {
        // 引导辅助程序
        EventLoopGroup boss = new NioEventLoopGroup();
        // 通过nio方式来接收连接和处理连接
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new EchoServerHandler());
                        }
                    });

            // 开启服务端
            ChannelFuture future = boot.bind(8090).sync();

            // 等待关闭
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
