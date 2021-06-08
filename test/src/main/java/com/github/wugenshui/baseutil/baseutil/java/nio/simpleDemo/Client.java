package com.github.wugenshui.baseutil.baseutil.java.nio.simpleDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : chenbo
 * @date : 2021-02-21
 */
public class Client {
    public static void main(String[] args) {
        // 连接地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);

        // 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 连接通道
        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(address);
            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);

                buffer.put(bytes);
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
