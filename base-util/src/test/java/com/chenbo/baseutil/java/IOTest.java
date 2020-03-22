package com.chenbo.baseutil.java;

import com.chenbo.baseutil.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * @author : chenbo
 * @date : 2020-03-22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        System.out.println(bufferedReader.readLine());

        bufferedReader.close();
    }
}
