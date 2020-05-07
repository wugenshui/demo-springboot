package com.chenbo.baseutil.java.io;

import com.chenbo.baseutil.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * @author : chenbo
 * @date : 2020-03-22
 */
@SpringBootTest
public class IOTest {
    @Test
    public void OutputStreamTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");

        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeBytes(user.toString());

        dataOutputStream.flush();
        dataOutputStream.close();
    }

    @Test
    public void InputStreamTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("output.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        //dataInputStream.
        byte[] b = new byte[20];
        int len;
        while ((len = dataInputStream.read(b)) != -1) {
            String str = new String(b);
            System.out.println(str);
            System.out.println(len);
        }

        dataInputStream.close();

        // 推荐方式
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("output.txt")));
        System.out.println(bufferedReader.readLine());

        bufferedReader.close();
    }
}
