package com.chenbo.baseutil.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @author : chenbo
 * @date : 2020-03-23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NIOTest {

    @Test
    public void intBufferTest() {
        IntBuffer buffer = IntBuffer.allocate(5);

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
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
}
