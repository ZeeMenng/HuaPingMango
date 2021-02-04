package com.zee.app.start;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zee.set.config.MybatisConfig;

@SpringBootApplication
@Configuration
@Import({ MybatisConfig.class })
@ComponentScan(basePackages = { "com.**.base.**", "com.**.custom.**", "com.**.gp.**", "com.**.pi.**", "com.zee.set.**", "com.zee.utl.**" }, excludeFilters = { @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { com.zee.utl.Executors.class, com.zee.utl.MongoUtil.class, com.zee.app.custom.MongodbController.class, com.zee.utl.service.DaUserContributionUtil.class }), @Filter(type = FilterType.REGEX, pattern = "com.zee.utl.crawler.*"), @Filter(type = FilterType.REGEX, pattern = "com.zee.utl.task.*") })
// 屏蔽MongoDB自动连接
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableScheduling
@EnableCaching
public class Start {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Start.class);
		app.run(args);
	}
}