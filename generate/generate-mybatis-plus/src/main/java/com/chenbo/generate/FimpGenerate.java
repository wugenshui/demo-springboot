package com.chenbo.generate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 林业综合管理系统
 *
 * @author : chenbo
 * @date : 2021-01-24
 */
public class FimpGenerate {

    /**
     * 自动生成包名配置
     */
    private static final HashMap<String, String> autoGenerateModuleMap = new HashMap<String, String>() {{
        put("SYS", "system");
        put("NAT", "nature");
    }};

    public static void main(String[] args) {
        while (true) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 输出目录
            String outputDir = "D:\\WorkSpace\\fimp\\booking_visit_service\\booking-visit-api";
            // 开发人员
            String author = "chenbo";

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:oracle:thin:@192.168.0.204:1521:helowin");
            dsc.setDriverName("oracle.jdbc.OracleDriver");
            dsc.setUsername("simp");
            dsc.setPassword("abcd-1234");
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            String tablesStr = scanner("表名，多个英文逗号分割，自动识别第一个表的模块（需先配置 autoGenerateModuleMap）");
            String[] tables = tablesStr.split(",");
            pc.setParent("com.huaweisoft.fimp.booking.visit.api");
            mpg.setPackageInfo(pc);

            // *********************************************************************************************

            // 自动获取包名
            String modelKey = "";
            String moduleName = null;
            for (Map.Entry<String, String> entiy : autoGenerateModuleMap.entrySet()) {
                if (tables[0].startsWith(entiy.getKey())) {
                    modelKey = entiy.getKey();
                    moduleName = entiy.getValue();
                    break;
                }
            }
            if (StringUtils.isEmpty(moduleName)) {
                moduleName = scanner("模块名");
            }
            pc.setModuleName(moduleName);

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            // 【输出文件路径】
            gc.setOutputDir(outputDir + "/src/main/java");
            // 是否覆盖已有文件,谨慎开启
            gc.setFileOverride(true);
            gc.setAuthor(author);
            gc.setEnableCache(false);
            gc.setOpen(false);
            // 实体属性 Swagger2 注解
            gc.setSwagger2(true);
            gc.setBaseResultMap(false);
            gc.setBaseColumnList(false);
            gc.setServiceName("%sService");
            gc.setDateType(DateType.ONLY_DATE);
            mpg.setGlobalConfig(gc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("VOPackageName", this.getConfig().getPackageInfo().get("Entity").replace("entity", "vo"));
                    this.setMap(map);
                }
            };
            mpg.setCfg(cfg);

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // xml文件模板
            String mapTemplatePath = "/templates/mapper.xml.vm";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(mapTemplatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return outputDir + "/src/main/resources/mapper/" + (StringUtils.isNotBlank(pc.getModuleName()) ? pc.getModuleName() + "/" : "")
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            String voTemplatePath = "/templates/vo.java.vm";
            focList.add(new FileOutConfig(voTemplatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出路径
                    return outputDir + "/src/main/java/" + pc.getParent().replace('.', '/')
                            + "/vo/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
                }
            });
            cfg.setFileOutConfigList(focList);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();

            // 配置自定义输出模板
            // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            // 自定义实体层模板路径
            // templateConfig.setEntity("templates/entity2.java");
            // templateConfig.setService(); // 自定义服务层模板路径
            // templateConfig.setController(); // 自定义控制器层模板路径
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            // 写于父类中的公共字段
            // strategy.setSuperEntityColumns("id");  // 设置父类属性，例如id等公用属性不需要每个实体写一次
            // strategy.setLikeTable(new LikeTable(scanner("表名,模糊匹配")));
            strategy.setInclude(tables);
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setTablePrefix(pc.getModuleName() + "_", modelKey);
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new VelocityTemplateEngine());

            mpg.execute();
        }
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
