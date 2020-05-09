package com.chenbo.demo.poi.tl.dbWord;

import com.chenbo.demo.poi.tl.entity.TableFileds;
import com.chenbo.demo.poi.tl.entity.Tables;
import com.chenbo.demo.poi.tl.mapper.TableMapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import lombok.val;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DbWordTest {
    @Autowired
    private TableMapper mapper;

    @Autowired
    private DataSource dataSource;

    @Test
    @Ignore
    public void dbWordTest() throws IOException, SQLException {

        Connection connection = dataSource.getConnection();
        String dbName = connection.getCatalog();
        List<Tables> allTables = mapper.getAllTables(dbName);

        DbData db = new DbData();
        List<TablePartData> tables = new ArrayList<>();

        allTables.forEach(t -> {
            List<TableFileds> fileds = mapper.getTable(t.getName());
            val rowData = new ArrayList<RowData>();
            fileds.forEach(f -> {
                rowData.add(new RowData(f.getField(), f.getType(), ("PRI".equals(f.getKey()) ? "是" : ""), f.getDefault(), f.getComment()));
            });
            tables.add(TablePartData.builder().title(t.getComment() + "：" + t.getName()).rows(rowData).build());
        });

        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("rows", policy).build();

        db.setTables(new DocxRenderData(new File("src/test/resources/table.docx"), tables));

        // 解决异常： Zip bomb detected! The file would exceed the max.
        // 延迟解析比率
        ZipSecureFile.setMinInflateRatio(-1.0d);

        XWPFTemplate.compile("src/test/resources/db.docx", config)
                .render(db)
                .writeToFile("src/test/resources/" + dbName + ".docx");

    }

}
