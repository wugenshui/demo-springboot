package com.chenbo.demo.poi.tl;

import com.deepoove.poi.XWPFTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PoiTlApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("path = " + path);
        XWPFTemplate template = XWPFTemplate.compile(path + "/template.docx")
                .render(new HashMap<String, Object>() {{
                    put("title", "Poi-tl 模板引擎标题");
                    put("content", "这里是正文");
                }});
        FileOutputStream out = new FileOutputStream(path + "/out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
