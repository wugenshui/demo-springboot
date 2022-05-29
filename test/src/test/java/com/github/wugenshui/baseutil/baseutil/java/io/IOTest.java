package com.github.wugenshui.baseutil.baseutil.java.io;

import com.github.wugenshui.baseutil.baseutil.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

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

        // 不管嵌套多少层流，只需刷新关闭最新的一个流即可
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    @Test
    public void InputStreamTest() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("output.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
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

    @Test
    public void fileTest() {
        File file = new File("E://a/b.jpg");
        System.out.println(file.exists());
        System.out.println(file.canExecute());
        System.out.println(file.isFile());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getFreeSpace());
        System.out.println(file.getParentFile());
        System.out.println(file.getParent());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getUsableSpace());
    }
}
