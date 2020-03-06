package com.chenbo.demo.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-03-06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Test
    public void exportExxcel() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<String, String>();
            map.put("name", "1" + i);
            map.put("sex", "2" + i);
            list.add(map);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("2412312", "测试", "测试"),
                CourseEntity.class, list);
    }
}
