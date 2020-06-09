package com.chenbo.baseutil.java.io;

import com.chenbo.baseutil.bean.StudentVO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : chenbo
 * @date : 2020-06-09
 */
@SpringBootTest
public class SerializableTest {
    @Test
    public void serializeTest() throws IOException {
        // 忽略 transient属性
        StudentVO studentVO = StudentVO.builder().name("张三").tempName("李四").build();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serialize.txt"));
        oos.writeObject(studentVO);
        oos.close();
        System.out.println("序列化对象成功");
    }

    @Test
    public void deSerializeTest() throws IOException, ClassNotFoundException {
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream("serialize.txt"));
        StudentVO studentVO = (StudentVO) oos.readObject();
        oos.close();
        System.out.println("反序列化对象成功" + studentVO);
    }
}
