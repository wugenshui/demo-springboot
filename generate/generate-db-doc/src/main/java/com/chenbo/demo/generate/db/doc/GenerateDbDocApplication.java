package com.chenbo.demo.generate.db.doc;

import com.chenbo.demo.generate.db.doc.config.ExportConfig;
import com.chenbo.demo.generate.db.doc.entity.*;
import com.chenbo.demo.generate.db.doc.mapper.BaseTableMapper;
import com.chenbo.demo.generate.db.doc.mapper.MySqlTableMapper;
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

        ExportConfig exportConfig = context.getBean(ExportConfig.class);
        DataSource dataSource = context.getBean(DataSource.class);
        // 通过驱动类自动识别需要使用的 mapper,默认为 mysql
        String driverClassName = context.getEnvironment().getProperty("spring.datasource.driver-class-name");
        BaseTableMapper mapper;
        switch (driverClassName) {
            case "oracle.jdbc.OracleDriver":
                mapper = context.getBean(OracleTableMapper.class);
                break;
            default:
                mapper = context.getBean(MySqlTableMapper.class);
        }

        List<String> ignoreTables = Arrays.asList(exportConfig.getIgnoreTables().split(","));

        Connection connection = dataSource.getConnection();
        String dbName = connection.getCatalog();
        List<Tables> allTables = mapper.getAllTables(dbName);
        log.info("查询到数据表总数：" + allTables.size());

        DbData db = new DbData();
        List<TablePartData> tables = new ArrayList<>();

        for (int i = 0; i < allTables.size(); i++) {
            Tables table = allTables.get(i);
            if (!ignoreTables.contains(table.getName())) {
                log.info("查询：{} {}/{}", table.getName(), i + 1, allTables.size());
                List<TableFileds> fileds = mapper.getTable(table.getName());
                val rows = new ArrayList<RowData>();
                fileds.forEach(f -> {
                    RowData row = new RowData();
                    row.setName(f.getField());
                    row.setType(f.getType());
                    // Oracle=P Mysql=PRI
                    row.setKey((f.getKey() != null && f.getKey().contains("P")) ? "√" : "");
                    row.setDefaultValue(f.getDefault());
                    row.setRemark(f.getComment());
                    // Oracle=N Mysql=NO
                    row.setIsNull(f.getNull().contains("N") ? "√" : "");
                    if (f.getType().equals("NUMBER")) {
                        row.setPrecision(f.getPrecision());
                        row.setScale(f.getScale());
                    } else {
                        row.setPrecision(f.getLength());
                    }
                    rows.add(row);
                });
                tables.add(TablePartData.builder().title(table.getComment() + "：" + table.getName()).rows(rows).build());
            } else {
                log.info("忽略：{} {}/{}", table.getName(), i + 1, allTables.size());
            }
        }

        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("rows", policy).build();

        File tableFile = ResourceUtils.getFile(exportConfig.getTableTemplate());
        db.setTables(new DocxRenderData(tableFile, tables));

        // 解决异常： Zip bomb detected! The file would exceed the max.
        // 延迟解析比率
        ZipSecureFile.setMinInflateRatio(-1.0d);

        File dbFile = ResourceUtils.getFile(exportConfig.getDbTemplate());
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File homeDirectory = fileSystemView.getHomeDirectory();
        XWPFTemplate.compile(dbFile, config)
                .render(db)
                .writeToFile(homeDirectory.getPath() + "/" + dbName + ".docx");
    }
}
