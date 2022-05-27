package com.github.wugenshui.freemarker.service;

import cn.hutool.core.io.FileUtil;
import com.github.wugenshui.freemarker.model.JavaProperty;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        // 设置模板文件所在的路径
        configuration.setClassForTemplateLoading(GenerateService.class, "/java");

        File targetDirectory = new File(directory);
        FileUtil.del(targetDirectory);
        FileUtil.mkdir(targetDirectory);
        log.info("mkdir:{}", targetDirectory.getAbsolutePath());

        writeFile(directory, ".gitignore.ftl.ftl", "", property);
        writeFile(directory, "App.java.ftl", "src/main/java/" + property.getDirectory(), property);
        copyFile(directory, "application.yml", "src/main/resources/");
        copyFile(directory, "application-dev.yml", "src/main/resources/");
        copyFile(directory, "application-prod.yml", "src/main/resources/");
        copyFile(directory, "application-test.yml", "src/main/resources/");
        copyFile(directory, "deploy.sh.ftl", "src/main/resources/");
        copyFile(directory, "Dockerfile", "src/main/resources/");
        copyFile(directory, "Jenkinsfile", "");
        writeFile(directory, "pom.xml.ftl", "", property);
        writeFile(directory, "AppTests.java.ftl", "src/test/java/" + property.getDirectory(), property);
    }


    /**
     * 生成vuepress模板代码
     */
    public void generateVuePress(JavaProperty property) throws Exception {
        String directory = "vuepress/";

        // 设置模板文件所在的路径
        configuration.setClassForTemplateLoading(GenerateService.class, "/vuepress");

        File targetDirectory = new File(directory);
        FileUtil.del(targetDirectory);
        FileUtil.mkdir(targetDirectory);
        log.info("mkdir:{}", targetDirectory.getAbsolutePath());

        writeFile(directory, "deploy.sh.ftl", "docs/.vuepress/public/", property);
        copyFile(directory, "Dockerfile", "docs/.vuepress/public/");
        copyFile(directory, "favicon.ico", "docs/.vuepress/public/");
        copyFile(directory, "logo.png", "docs/.vuepress/public/");
        copyFile(directory, "palette.scss", "docs/.vuepress/styles/");
        writeFile(directory, "config.js.ftl", "docs/.vuepress/", property);
        copyFile(directory, "foo.md", "docs/guide/");
        copyFile(directory, "gREADME.md", "docs/guide/", "README.md");
        copyFile(directory, "dREADME.md", "docs/", "README.md");
        writeFile(directory, ".gitignore.ftl", "", property);
        copyFile(directory, "Jenkinsfile", "");
        writeFile(directory, "package.json.ftl", "", property);
        copyFile(directory, "README.md", "");
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
            log.info("writeFile:{}->{}", templateName, targetDirectory.getAbsolutePath());
        } catch (Exception e) {
            log.error("文件写入失败", e);
        }
    }

    private void copyFile(String directory, String templateName, String outputDirectory) throws Exception {
        copyFile(directory, templateName, outputDirectory, templateName);
    }

    private void copyFile(String directory, String templateName, String outputDirectory, String outputFileName) throws Exception {
        File targetDirectory = new File(directory + outputDirectory);
        if (!FileUtil.exist(targetDirectory)) {
            log.info("mkdir:{}", targetDirectory.getAbsolutePath());
            FileUtil.mkdir(targetDirectory);
        }
        ClassPathResource source = new ClassPathResource(directory + templateName);
        InputStream input = source.getInputStream();

        OutputStream os = new FileOutputStream(directory + outputDirectory + outputFileName);
        int len = 2048;
        byte[] buffer = new byte[len];

        while ((len = input.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, len);
        }
        os.close();
        input.close();

        log.info("copyFile:{}->{}", templateName, targetDirectory.getAbsolutePath());
    }
}
