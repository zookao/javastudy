package com.zookao;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * User: zookao
 * Date: 2021-10-22
 */
public class CodeGenerate {
    @Test
    public void testGenerate(){
        AutoGenerator ag = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取当前目录
        gc.setOutputDir(projectPath+"/src/main/java");//输出到哪个目录
        gc.setAuthor("zookao");
        gc.setOpen(false);
        gc.setFileOverride(true);//是否覆盖
        gc.setServiceName("%sService");//去Service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("java");
        dsc.setPassword("Java@123");
        dsc.setUrl("jdbc:mysql://39.97.224.82:3306/java_learning?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();
        pc.setModuleName("exercise");
        pc.setParent("com.zookao");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        // pc.setService("service");
        // pc.setController("controller");
        ag.setPackageInfo(pc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user_caozongchao");//设置要映射的表名,只需改这里即可
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//是否使用lombok开启注解
        // strategy.setLogicDeleteFieldName("deleted");

        // TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        // TableFill gmtUpdate = new TableFill("gmt_update", FieldFill.INSERT_UPDATE);
        // ArrayList<TableFill> tableFills = new ArrayList<>();
        // tableFills.add(gmtCreate);
        // tableFills.add(gmtUpdate);
        // strategy.setTableFillList(tableFills);

        // strategy.setVersionFieldName("version");
        // strategy.setRestControllerStyle(true);//开启驼峰命名
        // strategy.setControllerMappingHyphenStyle(true);//localhost:8080/hello_id_2
        ag.setStrategy(strategy);

        ag.execute();//执行
    }
}
