package com.chenbo.baseutil.java.aio.simpleDemo;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : chenbo
 * @date : 2021-02-22
 */
public class Server {
    // 线程池
    private ExecutorService executorService;
    // 线程组
    public AsynchronousChannelGroup channelGroup;
    // 服务器通道
    public AsynchronousServerSocketChannel serverChannel;

    public Server(int port) {
        try {
            executorService = Executors.newCachedThreadPool();
            channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            serverChannel = AsynchronousServerSocketChannel.open(channelGroup);
            serverChannel.bind(new InetSocketAddress(port));

            System.out.println("Server Start, Port: " + port);
            serverChannel.accept(this, new ServerCompletionHandle());
            // 一直阻塞，不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(8765);
    }
}
