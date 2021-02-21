package com.chenbo.baseutil.java.nio.simpleDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author : chenbo
 * @date : 2021-02-21
 */
public class Server implements Runnable {
    // 多路复用器，管理所有的通道
    private Selector selector;
    // 缓冲区
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            // 打开多路复用器
            this.selector = Selector.open();
            // 打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 设置服务器通道为非阻塞模式
            ssc.configureBlocking(false);
            // 绑定地址
            ssc.bind(new InetSocketAddress(port));
            // 把服务器注册到多路复用器，并且监听阻塞事件
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server Start, Port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 让多路复用器开始监听
                this.selector.select();
                // 返回多路复用器选择的结果集
                Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    // 有效的
                    if (key.isValid()) {
                        // 阻塞状态
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                        // 可读状态
                        if (key.isReadable()) {
                            this.read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        try {
            // 清空缓存区旧数据
            buffer.clear();
            // 获取之前注册的Socket通道对象
            SocketChannel channel = (SocketChannel) key.channel();
            int count = channel.read(buffer);
            // 无数据读取
            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            // 有数据读取，读取前先复位
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            // 接受缓冲区数据
            buffer.get(bytes);
            // 打印结果
            String body = new String(bytes).trim();
            System.out.println("Server:" + body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel channel = ssc.accept();
            channel.configureBlocking(false);
            // 注册至多路复用器，并设置读取标志
            channel.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8765)).start();
    }
}
