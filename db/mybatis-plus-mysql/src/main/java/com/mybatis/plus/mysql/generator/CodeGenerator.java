package com.mybatis.plus.mysql.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author : chenbo
 * @date : 2021-12-17
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + "/db/mybatis-plus-mysql/";
        FastAutoGenerator.create("jdbc:p6spy:mysql://localhost:3306/test_school?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder
                            .author("chenbo") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder
                            .parent("com.mybatis.plus.mysql") // 设置父包名
                            .moduleName("sims") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sims_college") // 设置需要生成的表名
                            .addTablePrefix("sims_"); // 设置过滤表前缀
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
