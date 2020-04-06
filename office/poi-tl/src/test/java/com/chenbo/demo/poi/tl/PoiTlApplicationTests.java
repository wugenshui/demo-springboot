package com.chenbo.demo.poi.tl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.HyperLinkTextRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PoiTlApplicationTests {

    @Test
    @Ignore
    public void wordExportTest() throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("title", "Poi-tl 模板引擎标题");
        data.put("content", "这里是正文");
        data.put("link", new HyperLinkTextRenderData("Poi-tl Documentation", "http://deepoove.com/poi-tl/"));

        Style headTextStyle = new Style();
        TableStyle headStyle = new TableStyle();
        TableStyle rowStyle = new TableStyle();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("7F7F7F");

        // table
        headStyle.setBackgroundColor("F2F2F2");
        headStyle.setAlign(STJc.CENTER);
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
        RowRenderData header = RowRenderData.build(new TextRenderData("日期", headTextStyle),
                new TextRenderData("订单编号", headTextStyle), new TextRenderData("销售代表", headTextStyle),
                new TextRenderData("离岸价", headTextStyle), new TextRenderData("发货方式", headTextStyle),
                new TextRenderData("条款", headTextStyle), new TextRenderData("税号", headTextStyle));
        header.setRowStyle(headStyle);
        RowRenderData row = RowRenderData.build("2018-06-12", "SN18090", "李四", "5000元", "快递", "附录A", "T11090");
        row.setRowStyle(rowStyle);
        MiniTableRenderData miniTableRenderData = new MiniTableRenderData(header, Arrays.asList(row),
                MiniTableRenderData.WIDTH_A4_MEDIUM_FULL);
        miniTableRenderData.setStyle(headStyle);
        data.put("order", miniTableRenderData);

//        RowRenderData header = RowRenderData.build(new TextRenderData("FFFFFF", "姓名"), new TextRenderData("FFFFFF", "学历"));
//        RowRenderData row0 = RowRenderData.build("张三", "研究生");
//        RowRenderData row1 = RowRenderData.build("李四", "博士");
//        RowRenderData row2 = RowRenderData.build("王五", "小学生");
//
//        data.put("table", new MiniTableRenderData(header, Arrays.asList(row0, row1, row2)));

        // 自定义table
        DetailData detailData = new DetailData();
        detailData.setGoods(new ArrayList<RowRenderData>() {{
            add(RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600"));
            add(RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600"));
        }});
        detailData.setLabors(new ArrayList<RowRenderData>() {{
            add(RowRenderData.build("油漆工", "2", "200", "400"));
        }});
        data.put("detail_table", detailData);

        Configure config = Configure.newBuilder().bind("detail_table", new DetailTablePolicy()).build();
        XWPFTemplate.compile("src/test/resources/template.docx", config)
                .render(data)
                .writeToFile("src/test/resources/out_template.docx");
    }

}
