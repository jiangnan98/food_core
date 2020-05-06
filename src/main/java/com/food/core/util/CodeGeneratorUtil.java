package com.food.core.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author jiang
 *
 */
public class CodeGeneratorUtil {

	public static void main(String[] args) {
		String tableName="order_product";
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String path="D:\\CodeGenerator";
		gc.setOutputDir(path);
		gc.setOpen(false);
		gc.setFileOverride(true);// 是否覆盖同名文件，默认是false
		gc.setAuthor("Jiang");
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://59.110.116.148:3306/food?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT&tinyInt1isBit=true");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("food");
		dsc.setPassword("xNkxdkjwdz8ECpZ7");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.bing.jiang");
		mpg.setPackageInfo(pc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setInclude(tableName);
		strategy.setSuperEntityColumns("id");
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
