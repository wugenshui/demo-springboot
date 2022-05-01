package com.github.wugenshui.freemarker.service;

import cn.hutool.core.io.FileUtil;
import com.github.wugenshui.freemarker.model.JavaProperty;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author : chenbo
 * @date : 2022-04-30
 */
@Service
@Slf4j
public class GenerateService {

    @Autowired
    private Configuration configuration;

    @Bean
    public Configuration configuration() throws IOException {
        // 创建一个Configuration对象，直接new一个对象。构造方法的参数就是FreeMarker对于的版本号
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 设置模板文件使用的字符集。一般就是utf-8
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }

    /**
     * 生成java模板代码
     */
    public void generateJava(JavaProperty property) throws Exception {

        // // 创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        // Map dataModel = new HashMap<>(12);
        // // 向数据集中添加数据
        // dataModel.put("java", property);

        String directory = "java/";

        File directoryFile = FileUtil.file(directory);
        // 设置模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(directoryFile);

        File targetDirectory = new File(directory);
        FileUtil.del(targetDirectory);
        FileUtil.mkdir(targetDirectory);
        log.info("mkdir:{}", targetDirectory.getAbsolutePath());

        writeFile(directory, ".gitignore.ftl", "", property);
        writeFile(directory, "App.java.ftl", "src/main/java/" + property.getDirectory(), property);
        copyFile(directory, "application.yml", "src/main/resources/");
        copyFile(directory, "application-dev.yml", "src/main/resources/");
        copyFile(directory, "application-prod.yml", "src/main/resources/");
        copyFile(directory, "application-test.yml", "src/main/resources/");
        copyFile(directory, "deploy.sh", "src/main/resources/");
        copyFile(directory, "Dockerfile", "src/main/resources/");
        copyFile(directory, "Jenkinsfile", "");
        writeFile(directory, "pom.xml.ftl", "", property);
        writeFile(directory, "AppTests.java.ftl", "src/test/java/" + property.getDirectory(), property);
    }

    private void writeFile(String prefix, String templateName, String outputDirectory, Object dataModel) {
        File targetDirectory = new File(prefix + outputDirectory);
        if (!FileUtil.exist(targetDirectory)) {
            log.info("mkdir:{}", targetDirectory.getAbsolutePath());
            FileUtil.mkdir(targetDirectory);
        }
        // 创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        String outputPath = prefix + outputDirectory + templateName.replace(".ftl", "");
        try (Writer out = new FileWriter(outputPath)) {
            // 加载一个模板，创建一个模板对象。
            Template template = configuration.getTemplate(templateName);
            // 调用模板对象的process方法输出文件。
            template.process(dataModel, out);
        } catch (Exception e) {
            log.error("文件写入失败", e);
        }
    }

    private void copyFile(String directory, String templateName, String outputDirectory) throws FileNotFoundException {
        File targetDirectory = new File(directory + outputDirectory);
        if (!FileUtil.exist(targetDirectory)) {
            log.info("mkdir:{}", targetDirectory.getAbsolutePath());
            FileUtil.mkdir(targetDirectory);
        }

        String sourceFile = FileUtil.getAbsolutePath(directory + templateName);
        FileUtil.copyFile(sourceFile, directory + outputDirectory + templateName);
        log.info("copyFile:{}->{}", sourceFile, directory + outputDirectory + templateName);
    }
}
