//package com.example.boot009;
//
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//@SpringBootTest
//public class GeneratorCodeConfig {
//    public static void main(String[] args) {
//        //构建一个代码生成器对象
//        AutoGenerator mpg = new AutoGenerator();
//        //配置策略
//        //1、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("刘兴俊");
//        gc.setOpen(false);//取消打开资源管理器
//        gc.setFileOverride(false);//取消覆盖原来生成的
//        gc.setServiceName("%sService");//去service的I前缀
//        gc.setIdType(IdType.AUTO);
//        gc.setDateType(DateType.ONLY_DATE);
//        gc.setSwagger2(true);
//
//        mpg.setGlobalConfig(gc);
//        //2、设置数据源配置策略
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/library?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("123456");
//        dataSourceConfig.setDbType(DbType.MYSQL);
//
//        mpg.setDataSource(dataSourceConfig);
//        //3、包的配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("blog");
//        pc.setParent("com.lxj");//生成的包名为com.lxj.blog
//        pc.setEntity("entity");
//        pc.setMapper("mapper");
//        pc.setService("service");
//        pc.setController("controller");
//
//        mpg.setPackageInfo(pc);
//        //4、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("user");//映射表名
//        strategy.setNaming(NamingStrategy.underline_to_camel);//下划线转驼峰
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityLombokModel(true);
//        strategy.setLogicDeleteFieldName("deleted");
//        //自动填充配置
//        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
//        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.UPDATE);
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(gmtCreate);
//        tableFills.add(gmtModified);
//        strategy.setTableFillList(tableFills);
//        //乐观锁
//        strategy.setVersionFieldName("version");
//        strategy.setRestControllerStyle(true);//开启RestForm的驼峰命名
//        strategy.setControllerMappingHyphenStyle(true);//l能够写成这样ocalhost:8080/hello_id_2
//        mpg.setStrategy(strategy);
//
//        mpg.execute();//执行
//    }
//
//
//}
