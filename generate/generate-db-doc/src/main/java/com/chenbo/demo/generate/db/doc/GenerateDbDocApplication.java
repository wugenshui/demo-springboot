package com.chenbo.demo.generate.db.doc;

import com.chenbo.demo.generate.db.doc.config.ExportConfig;
import com.chenbo.demo.generate.db.doc.entity.*;
import com.chenbo.demo.generate.db.doc.mapper.OracleTableMapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文档生成
 *
 * @author : chenbo
 * @date : 2021-01-25
 */
@SpringBootApplication
@Slf4j
public class GenerateDbDocApplication {
    public static void main(String[] args) throws SQLException, IOException {
        ConfigurableApplicationContext context = SpringApplication.run(GenerateDbDocApplication.class, args);

        OracleTableMapper mapper = context.getBean(OracleTableMapper.class);
        DataSource dataSource = context.getBean(DataSource.class);
        ExportConfig exportConfig = context.getBean(ExportConfig.class);

        List<String> ignoreTables = Arrays.asList(exportConfig.getIgnoreTables().split(","));

        Connection connection = dataSource.getConnection();
        String dbName = connection.getCatalog();
        List<Tables> allTables = mapper.getAllTables(dbName);
        log.info("查询到数据表总数：" + allTables.size());

        DbData db = new DbData();
        List<TablePartData> tables = new ArrayList<>();

        for (int i = 0; i < allTables.size(); i++) {
            Tables table = allTables.get(i);
            if (!ignoreTables.contains(table.getName()) && table.getName().equals("SYS_USER")) {
                log.info("查询：{} {}/{}", table.getName(), i + 1, allTables.size());
                List<TableFileds> fileds = mapper.getTable(table.getName());
                val rowData = new ArrayList<RowData>();
                fileds.forEach(f -> {
                    if (f.getType().equals("NUMBER")) {
                        rowData.add(new RowData(f.getField(), f.getType(), ("P".equals(f.getKey()) ? "是" : ""), f.getDefault(), f.getComment(), f.getPrecision(), f.getScale()));
                    } else {
                        rowData.add(new RowData(f.getField(), f.getType(), ("P".equals(f.getKey()) ? "是" : ""), f.getDefault(), f.getComment(), f.getLength(), null));
                    }
                });
                tables.add(TablePartData.builder().title(table.getComment() + "：" + table.getName()).rows(rowData).build());
            } else {
                log.info("忽略：" + table.getName());
            }
        }

        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("rows", policy).build();

        File tableFile = ResourceUtils.getFile("classpath:hw-table.docx");
        db.setTables(new DocxRenderData(tableFile, tables));

        // 解决异常： Zip bomb detected! The file would exceed the max.
        // 延迟解析比率
        ZipSecureFile.setMinInflateRatio(-1.0d);

        File dbFile = ResourceUtils.getFile("classpath:hw-db.docx");
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File homeDirectory = fileSystemView.getHomeDirectory();
        XWPFTemplate.compile(dbFile, config)
                .render(db)
                .writeToFile(homeDirectory.getPath() + "/" + dbName + ".docx");
    }
}
