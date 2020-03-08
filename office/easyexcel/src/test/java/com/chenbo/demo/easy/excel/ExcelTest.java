package com.chenbo.demo.easy.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author : chenbo
 * @date : 2020-03-08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Test
    public void readExcelTest() {
        String fileName = ExcelTest.class.getResource("/").getPath() + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    @Test
    public void writeExcelTest() {

    }
}
