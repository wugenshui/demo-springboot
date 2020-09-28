package com.chenbo.baseutil.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.chenbo.baseutil.hutool.entity.CloneEntity;
import com.chenbo.baseutil.hutool.entity.DeepEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@SpringBootTest
public class CloneTest {

    @Test
    public void serializeTest() {
        CloneEntity source = new CloneEntity();
        int[] ints = {1, 2, 3};
        source.setDeep(new DeepEntity("张阿森纳"));
        source.setInts(ints);
        source.setAge(18);

        try {
            String filePath = "CloneObject.ser";
            // 序列化
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(source);
            out.close();
            fileOut.close();
            System.out.println("source = " + source);

            // 反序列化
            CloneEntity target = null;
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            target = (CloneEntity) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("target = " + target);

            // 删除文件
            boolean del = FileUtil.del(new File(filePath));
            System.out.println("删除文件 = " + del);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deepCloneTest() {
        CloneEntity source = new CloneEntity();
        int[] ints = {1, 2, 3};
        source.setDeep(new DeepEntity("张阿森纳"));
        source.setInts(ints);
        source.setAge(18);
        CloneEntity target = ObjectUtil.clone(source);
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertEquals(source.getAge(), target.getAge());
        Assert.assertEquals(source.getDeep().toString(), target.getDeep().toString());
        Assert.assertEquals(Arrays.toString(source.getInts()), Arrays.toString(target.getInts()));

        int[] ints1 = target.getInts();
        ints1[0] = 5;
        DeepEntity deepEntity = target.getDeep();
        deepEntity.setName("胡桃慧子");
        target.setAge(29);
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertNotEquals(source.getAge(), target.getAge());
        Assert.assertNotEquals(source.getDeep(), target.getDeep());
        Assert.assertNotEquals(source.getInts(), target.getInts());
    }
}
