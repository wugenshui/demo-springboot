package com.github.wugenshui.baseutil.baseutil.java.aio.simpleDemo;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * @author : chenbo
 * @date : 2021-02-22
 */
public class ServerCompletionHandle implements CompletionHandler<AsynchronousSocketChannel, Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        attachment.serverChannel.accept(attachment, this);
        read(result);
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }

    private void read(final AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                String resultData = new String(attachment.array()).trim();
                System.out.println("Server接受到客户端消息：" + resultData);
                String response = "服务器响应，收到了客户端发来的数据：" + resultData;
                write(channel, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel channel, String response) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(response.getBytes());
        buffer.flip();
        try {
            channel.write(buffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
