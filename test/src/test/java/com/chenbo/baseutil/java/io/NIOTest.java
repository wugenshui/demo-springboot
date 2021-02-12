package com.chenbo.baseutil.java.io;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @author : chenbo
 * @date : 2020-03-23
 */
@SpringBootTest
public class NIOTest {

    @Test
    public void intBufferTest() {
        IntBuffer buffer = IntBuffer.allocate(5);
        System.out.println("init");
        print(buffer);

        System.out.println("put");
        for (int i = 0; i < 2; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
            print(buffer);
        }

        System.out.println("flip");
        buffer.flip();
        print(buffer);

        System.out.println("get");
        while (buffer.hasRemaining()) {
            // System.out.println(buffer.get());
            buffer.get();
            print(buffer);
        }

        System.out.println("flip");
        buffer.flip();
        print(buffer);

        System.out.println("put");
        for (int i = 0; i < 2; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
            print(buffer);
        }
    }

    @Test
    public void fileChannelTest() throws Exception {
        // 写入
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        byte[] messages = "Hello World!".getBytes();

        for (int i = 0; i < messages.length; i++) {
            buffer.put(messages[i]);
        }

        buffer.flip();

        channel.write(buffer);

        fileOutputStream.close();

        // 读取文件
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character:" + (char) b);
        }

        fileInputStream.close();

        // 删除文件
        new File("1.txt").delete();
    }

    private void print(Buffer buffer) {
        System.out.print("capacity = " + buffer.capacity());
        System.out.print(" limit = " + buffer.limit());
        System.out.println(" position = " + buffer.position());
    }
}
