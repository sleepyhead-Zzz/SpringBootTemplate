package com.springboottemplate.infrastructure.mybatisplus;

import static ch.qos.logback.core.util.StringUtil.capitalizeFirstLetter;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.springboottemplate.common.core.base.BaseController;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Sleepyhead
 * @date: 2023-09-12 周二
 * @Version: 1.0
 * @Description:
 */
@SuppressWarnings("all")
public class CodeGenerator {

    /**
     * 指定具体项目模块
     */
    public static String MOUDLE_NAME = "domain";
    public static String AUTHOR_NAME = "Sleepyhead";
    /**
     * 数据源信息
     */
    public static String URL = "jdbc:mysql://localhost:3306/springboottemplate?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    public static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static String USERNAME = "root";
    public static String PASSWORD = "DpVq8mVGCmykwYvVVfWGTSWx";

    /**
     * 需要生成的表名前缀,若为空，则默认是需要生成的表名是 TABLE_SUFFIX
     */
    public static String TABLE_PREFIX = "inspection_";
    /**
     * 表前缀,生成实体类时，会自动去除表前缀，如：table: tb_task, class: Task
     */
    public static String TABLE_PREFIX_ENTITY = "";
    /**
     * 需要生成的表名后缀，多个用逗号隔开
     */
//    public static String TABLE_SUFFIX = "menu,role,user_role,role_menu";
    public static String TABLE_SUFFIX = "plan";

    /**
     * 包设置
     */
    public static String PARENT_PACKAGE = "com.springboottemplate.domain";
    public static String PARENT_MOUDLE_NAME = "inspection." + TABLE_SUFFIX;
    public static String MAPPER_PATH = "/src/main/resources/mapper/";
    ;
    /**
     * DTO/VO/QueryDTO 包名
     */
    public static String PARENT_DTO_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME + ".dto";
    public static String PARENT_VO_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME + ".vo";
    public static String PARENT_QUERY_DTO_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME + ".query";
    public static String PARENT_APPLICATION_SERVIE_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME;
    public static String PARENT_MODEL_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME + ".model";
    public static String PARENT_COMMAND_PACKAGE = PARENT_PACKAGE + "." + PARENT_MOUDLE_NAME + ".command";


    /**
     * DTO/VO/QueryDTO 自定义模板路径
     */
    public static String TEMPLATE_CONTROLLER_PATH = "template/java/controller.java.vm";
    public static String TEMPLATE_SERVICE_PATH = "template/java/db/service.java.vm";
    public static String TEMPLATE_SERVICEIMPL_PATH = "template/java/db/serviceImpl.java.vm";
    public static String TEMPLATE_MAPPER_PATH = "template/java/db/mapper.java.vm";
    public static String TEMPLATE_MAPPER_XML_PATH = "template/xml/mapper.xml.vm";
    public static String TEMPLATE_ENTITY_PATH = "template/java/db/entity.java.vm";

    public static String TEMPLATE_APPLICATION_SERVICE_PATH = "template/java/applicationService.java.vm";
    public static String TEMPLATE_MODEL_PATH = "template/java/model/model.java.vm";
    public static String TEMPLATE_MODELFACTORY_PATH = "template/java/model/modelFactory.java.vm";
    public static String TEMPLATE_ADD_COMMAND_PATH = "template/java/command/addCommand.java.vm";
    public static String TEMPLATE_UPDATE_COMMAND_PATH = "template/java/command/updateCommand.java.vm";
    public static String TEMPLATE_DTO_PATH = "template/java/dto/dto.java.vm";
    public static String TEMPLATE_VO_PATH = "template/java/dto/vo.java.vm";
    public static String TEMPLATE_QUERY_DTO_PATH = "template/java/query/query.java.vm";
    public static String TEMPLATE_PAGE_QUERY_DTO_PATH = "template/java/query/pageQuery.java.vm";


    /**
     * controller 层请求路径前缀
     */
    public static String REQUEST_PATH_PREFIX = "/";


    /**
     * DTO 忽略的字段
     */
    public static String DTO_IGNORE_FIELD = "id,createTime,updateTime,deleted";
    public static String ENTITY_IGNORE_FIELD = "creator_id,updater_id,create_time,update_time,deleted";

    /**
     * 是否继承基类BaseEntity
     */
    public static Boolean isExtendsFromBaseEntity = true;

    public static void main(String[] args) {
        if ("".equals(TABLE_SUFFIX)) {
            System.out.println("----->>>需要生成的表名为空");
            return;
        }

// 表拼接
        List<String> tables;
        if (TABLE_PREFIX.isEmpty()) {
            // 如果没有表前缀，直接使用 TABLE_SUFFIX 中的表名
            tables = Arrays.asList(TABLE_SUFFIX.split(","));
        } else {
            // 如果有表前缀，拼接每个表名
            tables = Arrays.stream(TABLE_SUFFIX.split(","))
                .map(table -> TABLE_PREFIX + table)  // 将前缀和表名拼接
                .collect(Collectors.toList());
        }
// 输出拼接后的表名
        System.out.println("表：");
        tables.forEach(System.out::println);

        FastAutoGenerator mpg = FastAutoGenerator.create(
            new Builder(URL, USERNAME, PASSWORD).driverClassName(DRIVER_NAME));

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        mpg.globalConfig(builder -> builder
            .disableOpenDir()
            .outputDir(projectPath + "/" + MOUDLE_NAME + "/src/main/java") // 设置输出目录
            .author(AUTHOR_NAME) // 设置作者名
            .enableSwagger() // 开启 Swagger 模式
            .dateType(DateType.ONLY_DATE) // 设置时间类型策略
            .commentDate("yyyy-MM-dd") // 设置注释日期格式
            .build());

        mpg.packageConfig(builder -> builder
            .parent(PARENT_PACKAGE) // 设置父包名
            .moduleName(PARENT_MOUDLE_NAME) // 设置父包模块名
            .entity("db") // 设置 Entity 包名
            .service("db") // 设置 Service 包名
            .serviceImpl("db") // 设置 Service Impl 包名
            .mapper("db") // 设置 Mapper 包名
//            .xml("mapper") // 设置 Mapper XML 包名
//            .controller("controller") // 设置 Controller 包名
            .build());

        mpg.injectionConfig(builder -> builder
            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("DTO" + StringPool.DOT_JAVA) //文件名称
                .templatePath(TEMPLATE_DTO_PATH)
                .packageName("dto")
                .build()
            )

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("VO" + StringPool.DOT_JAVA) //文件名称
                .templatePath(TEMPLATE_VO_PATH)
                .packageName("dto")
                .build()
            )

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Query" + StringPool.DOT_JAVA) //文件名称
                .templatePath(TEMPLATE_QUERY_DTO_PATH)
                .packageName("query")
                .build()
            )

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("PageQuery" + StringPool.DOT_JAVA) //文件名称
                .templatePath(TEMPLATE_PAGE_QUERY_DTO_PATH)
                .packageName("query")
                .build()
            )

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Controller.java")  // 生成的 Controller 文件名
                .templatePath(TEMPLATE_CONTROLLER_PATH)  // 自定义的 Controller 模板路径
                .packageName("controller")  // 目标包名
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("ApplicationService.java")
                .templatePath(TEMPLATE_APPLICATION_SERVICE_PATH)
                .packageName("")  // 目标包名
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return "Add" + capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Command.java")
                .templatePath(TEMPLATE_ADD_COMMAND_PATH)
                .packageName("command")
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return "Update" + capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Command.java")
                .templatePath(TEMPLATE_UPDATE_COMMAND_PATH)
                .packageName("command")
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Model.java")
                .templatePath(TEMPLATE_MODEL_PATH)
                .packageName("model")
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("ModelFactory.java")
                .templatePath(TEMPLATE_MODELFACTORY_PATH)
                .packageName("model")
                .build())

            .customFile(new CustomFile.Builder()
                .formatNameFunction(new Function<TableInfo, String>() {
                    @Override
                    public String apply(TableInfo tableInfo) {
                        return capitalizeFirstLetter((underlineToCamel(tableInfo.getName())));
                    }
                })
                .fileName("Entity.java")
                .templatePath(TEMPLATE_ENTITY_PATH)
                .packageName("db")
                .build())

//            .customFile(new CustomFile.Builder()
//                .fileName("Service.java")  // 生成的 Service 文件名
//                .templatePath(TEMPLATE_SERVICE_PATH)  // 自定义的 Service 模板路径
//                .packageName("db")  // 目标包名
//                .build())
//            .customFile(new CustomFile.Builder()
//                .fileName("ServiceImpl.java")  // 生成的 ServiceImpl 文件名
//                .templatePath(TEMPLATE_SERVICEIMPL_PATH)  // 自定义的 ServiceImpl 模板路径
//                .packageName("db")  // 目标包名
//                .build())
//            .customFile(new CustomFile.Builder()
//                .fileName("Mapper.java")  // 生成的 Mapper 文件名
//                .templatePath(TEMPLATE_MAPPER_PATH)  // 自定义的 Mapper 模板路径
//                .packageName("db")  // 目标包名
//                .build())
//            .customFile(new CustomFile.Builder()
//                .fileName("Mapper.xml")  // 生成的 Mapper XML 文件名
//                .templatePath(TEMPLATE_MAPPER_XML_PATH)  // 自定义的 Mapper XML 模板路径
//                .packageName("mapper")  // 目标包名
//                .build())

            .beforeOutputFile((tableInfo, objectMap) -> {
                String entityName = tableInfo.getEntityName();
                System.out.println("准备生成文件: " + tableInfo.getEntityName());
                System.out.println("表名: " + tableInfo.getComment());
                objectMap.put("entityName", entityName);  // 添加表名到 objectMap
                objectMap.put("tableName", capitalizeFirstLetter((underlineToCamel(tableInfo.getName()))));
                objectMap.put("tablename", (underlineToCamel(tableInfo.getName())));
                objectMap.put("addCommandClass",
                    "Add" + capitalizeFirstLetter(underlineToCamel(tableInfo.getName())) + "Command");
                objectMap.put("updateCommandClass",
                    "Update" + capitalizeFirstLetter(underlineToCamel(tableInfo.getName())) + "Command");
                objectMap.put("applicationPackage", PARENT_APPLICATION_SERVIE_PACKAGE);
                objectMap.put("modelPackage", PARENT_MODEL_PACKAGE);
                objectMap.put("commandPackage", PARENT_COMMAND_PACKAGE);
                objectMap.put("author", AUTHOR_NAME);
                objectMap.put("requestPathPrefix", REQUEST_PATH_PREFIX);
                objectMap.put("dtoPackage", PARENT_DTO_PACKAGE);
                objectMap.put("queryDtoPackage", PARENT_QUERY_DTO_PACKAGE);
                objectMap.put("dtoIgnoreFields", DTO_IGNORE_FIELD);
                objectMap.put("entityIgnoreFields", ENTITY_IGNORE_FIELD);
                objectMap.put("isExtendsFromBaseEntity", isExtendsFromBaseEntity);

            })
            .build());

        mpg.strategyConfig(builder -> builder
                .enableCapitalMode() // 开启大写命名
                .enableSkipView() // 开启跳过视图
                .disableSqlFilter() // 禁用 SQL 过滤
                .addInclude(tables) // 增加表匹配
                .addTablePrefix(TABLE_PREFIX) // 增加过滤表前缀
                .addFieldSuffix(TABLE_SUFFIX) // 增加过滤字段后缀

//            entity
                .entityBuilder()
                .disable()
//                .superClass(BaseEntity.class)
//                .disableSerialVersionUID()
//                .enableChainModel()
                .enableLombok()
//                .enableRemoveIsPrefix()
                .enableTableFieldAnnotation()
                .enableActiveRecord()
                .versionColumnName("version")
                // deleted的字段设置成tinyint  长度为1
                .logicDeleteColumnName("deleted")
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
//                .addSuperEntityColumns("id", "creator_id", "create_time", "updaterId", "updateTime")
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Column("creator_id", FieldFill.INSERT))
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                .addTableFills(new Property("updaterId", FieldFill.INSERT_UPDATE))
                .idType(IdType.AUTO)
                .formatFileName("%sEntity")
                .build()

                //controller
                .controllerBuilder()
                .superClass(BaseController.class)
                .enableHyphenStyle()
                .enableRestStyle()
                .formatFileName("%sController")
                // 禁止生成默认的Controller模板
                .disable()
                .build()

                //service
                .serviceBuilder()
//                .superServiceClass(BaseService.class)
//                .superServiceImplClass(BaseServiceImpl.class)
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .build()

                //mapper
                .mapperBuilder()
                .disableMapperXml()
                .superClass(BaseMapper.class)
//                .enableMapperAnnotation()
//                .enableBaseResultMap()
//                .enableBaseColumnList()
                .formatMapperFileName("%sMapper")
//                .formatXmlFileName("%sXml")
                .build()

        );

        mpg.execute();
    }

    private static String underlineToCamel(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] parts = str.toLowerCase().split("_");
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                result.append(parts[i]); // 第一个单词保持小写（后续由 capitalizeFirstLetter 处理）
            } else {
                result.append(capitalizeFirstLetter(parts[i])); // 后续单词首字母大写
            }
        }
        return result.toString();
    }
}
