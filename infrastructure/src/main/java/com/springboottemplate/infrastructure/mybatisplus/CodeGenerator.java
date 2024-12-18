package com.springboottemplate.infrastructure.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.springboottemplate.common.core.base.BaseController;
import com.springboottemplate.common.core.base.BaseEntity;
import java.util.Collections;
import lombok.Data;

@Data
@lombok.Builder
public class CodeGenerator {

    // 定义代码生成器所需的基本配置信息
    private String author;
    private String module;
    private String tableName;
    private String databaseUrl;
    private String username;
    private String password;
    private String parentPackage;
    private Boolean isExtendsFromBaseEntity;

    public static void main(String[] args) {
        // 配置数据库连接信息
        String databaseUrl = "jdbc:mysql://localhost:3306/springboottemplate";
        String username = "root";
        String password = "DpVq8mVGCmykwYvVVfWGTSWx";

        // 初始化代码生成器对象并设置基本参数
        CodeGenerator generator = CodeGenerator.builder()
            .databaseUrl(databaseUrl)
            .username(username)
            .password(password)
            .author("Sleepyhead")
            // 生成代码的目录路径
            .module("/springboottemplate-orm/target/generated-code")
            .parentPackage("com.springboottemplate")
            .tableName("sys_menu")
            // 决定是否继承基类
            .isExtendsFromBaseEntity(true)
            .build();

        // 生成代码
        generator.generateCode();
    }

    public void generateCode() {
        // 创建代码生成器并配置数据库连接、查询方式和关键词处理
        FastAutoGenerator generator = FastAutoGenerator.create(
            new Builder(databaseUrl, username, password)
                .keyWordsHandler(new MySqlKeyWordsHandler()));

        // 各种生成器配置
        globalConfig(generator);       // 全局配置
        packageConfig(generator);      // 包配置
        injectionConfig(generator);    // 自定义注入配置
        strategyConfig(generator);     // 生成策略配置

        // 默认使用 Velocity 模板引擎
        generator.templateEngine(new VelocityTemplateEngine());
        generator.execute();
    }

    // 全局配置
    private void globalConfig(FastAutoGenerator generator) {
        generator.globalConfig(
            builder -> builder
                // 指定生成的文件目录
                .outputDir(System.getProperty("user.dir") + "/SpringBootTemplate" + module + "/src/main/java")
                // 设置日期类型为 java.util 下的日期类型
                .dateType(DateType.ONLY_DATE)
                // 设置文件中的作者信息
                .author(author)
                // 开启 Swagger 注解生成
                .enableSwagger()
                // 设置生成文件中的注释日期格式
                .commentDate("yyyy-MM-dd")
                .build());
    }

    // 包配置
    private void packageConfig(FastAutoGenerator generator) {
        generator.packageConfig(builder -> builder
            // 设置父包名称
            .parent(parentPackage)
            .moduleName("orm")
            .entity("entity")
            .service("service")
            .serviceImpl("service.impl")
            .mapper("mapper")
            .xml("mapper.xml")
            .controller("controller")
            // 指定生成的 XML 文件的路径
            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + module
                + "/src/main/resources/mapper/system/test"))
            .build());
    }

    // 自定义注入配置
    private void injectionConfig(FastAutoGenerator generator) {
        generator.injectionConfig(builder -> {
            // 自定义代码生成过程中的行为
            builder.beforeOutputFile((tableInfo, objectMap) -> System.out.println("tableInfo: " +
                    tableInfo.getEntityName() + " objectMap: " + objectMap.size()))
                .build();
        });
    }

    // 策略配置
    private void strategyConfig(FastAutoGenerator generator) {
        generator.strategyConfig(builder -> {
            builder
                // 启用大写命名方式
                .enableCapitalMode()
                // 跳过视图
                .enableSkipView()
                // 禁用 SQL 过滤
                .disableSqlFilter()
                // 仅生成指定的表
                .addInclude(tableName);

            entityConfig(builder);      // 实体配置
            controllerConfig(builder);   // 控制器配置
            serviceConfig(builder);      // 服务配置
            mapperConfig(builder);       // Mapper 配置
        });
    }

    // 实体配置
    private void entityConfig(StrategyConfig.Builder builder) {
        Entity.Builder entityBuilder = builder.entityBuilder();

        entityBuilder
            // 启用 Lombok 注解
            .enableLombok()
            // 启用表字段注解
            .enableTableFieldAnnotation()
            // 使用 ActiveRecord 模式
            .enableActiveRecord()
            // 设置逻辑删除字段
            .logicDeleteColumnName("deleted")
            // 使用下划线转驼峰命名
            .naming(NamingStrategy.underline_to_camel)
            .columnNaming(NamingStrategy.underline_to_camel)
            // 字段自动填充配置
            .addTableFills(new Column("create_time", FieldFill.INSERT))
            .addTableFills(new Column("creator_id", FieldFill.INSERT))
            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
            .addTableFills(new Property("updaterId", FieldFill.INSERT_UPDATE))
            // 设置主键生成策略
            .idType(IdType.AUTO)
            // 设置生成的文件名格式
            .formatFileName("%sEntity");

        // 判断是否继承基类 BaseEntity
        if (isExtendsFromBaseEntity) {
            entityBuilder
                .superClass(BaseEntity.class)
                .addSuperEntityColumns("creator_id", "create_time", "creator_name", "updater_id", "update_time",
                    "updater_name", "deleted");
        }

        entityBuilder.build();
    }

    // 控制器配置
    private void controllerConfig(StrategyConfig.Builder builder) {
        builder.controllerBuilder()
            .superClass(BaseController.class)  // 继承自 BaseController
            .enableHyphenStyle()               // 启用连字符命名
            .enableRestStyle()                 // 启用 RESTful 风格
            .formatFileName("%sController")    // 文件名格式
            .build();
    }

    // 服务配置
    private void serviceConfig(StrategyConfig.Builder builder) {
        builder.serviceBuilder()
            .formatServiceFileName("%sService")        // 设置 Service 文件名格式
            .formatServiceImplFileName("%sServiceImpl")// 设置 ServiceImpl 文件名格式
            .build();
    }

    // Mapper 配置
    private void mapperConfig(StrategyConfig.Builder builder) {
        builder.mapperBuilder()
            .formatMapperFileName("%sMapper")          // 设置 Mapper 文件名格式
            .formatXmlFileName("%sMapper")             // 设置 XML 文件名格式
            .build();
    }
}
