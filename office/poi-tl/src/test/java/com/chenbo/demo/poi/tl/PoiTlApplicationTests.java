package com.chenbo.demo.poi.tl;

import com.deepoove.poi.XWPFTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PoiTlApplicationTests {

    @Test
    public void wordExportTest() throws IOException {
        XWPFTemplate.compile("src/test/resources/template.docx")
                .render(new HashMap<String, Object>() {{
                    put("title", "Poi-tl 模板引擎标题");
                    put("content", "这里是正文");
                }})
                .writeToFile("out_template.docx");
    }

//    @Test
//    public void dbWordTest() throws IOException {
//        XWPFTemplate.compile("src/test/resources/db.docx")
//                .render(new HashMap<String, Object>() {{
//                    put("title", "Poi-tl 模板引擎标题");
//                    put("content", "这里是正文");
//                }})
//                .writeToFile("out_db.docx");
//    }

}
