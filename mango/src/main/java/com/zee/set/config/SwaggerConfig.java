package com.zee.set.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:17:54
 * @updateDate 2017年4月13日 下午3:17:54
 * @description Swagger相关的配置
 */
// @Configuration
// @EnableSwagger2
public class SwaggerConfig {

	private List<Parameter> setHeaderToken() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization").description("token前缀：Bearer 注意有个空格").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}

	@Bean
	public Docket gpApi() {
		@SuppressWarnings("unchecked")
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.SWAGGER_2).groupName("GpApi").apiInfo(gpApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.globalOperationParameters(setHeaderToken()).pathMapping("/");
	}

	@Bean
	public Docket daApi() {
		@SuppressWarnings("unchecked")
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.SWAGGER_2).groupName("DaApiInfo").apiInfo(daApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.globalOperationParameters(setHeaderToken()).pathMapping("/");
	}

	@Bean
	public Docket mfApi() {
		@SuppressWarnings("unchecked")
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.SWAGGER_2).groupName("MfApiInfo").apiInfo(mfApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.globalOperationParameters(setHeaderToken()).pathMapping("/");
	}

	@Bean
	public Docket wpApi() {
		@SuppressWarnings("unchecked")
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.SWAGGER_2).groupName("wpApiInfo").apiInfo(wpApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.globalOperationParameters(setHeaderToken()).pathMapping("/");
	}

	private ApiInfo gpApiInfo() {
		return new ApiInfo("通用功能（gp）", // 标题
				"用户、角色、权限、日志、消息、附件等通用表的API文档。", // 描述
				"General Purpose", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo daApiInfo() {
		return new ApiInfo("数据采集（da）", // 标题
				"数据采集系统相关表的API文档", // 描述
				"Data Acquisition", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo mfApiInfo() {
		return new ApiInfo("监测预警（mf）", // 标题
				"数据采集系统相关表的API文档", // 描述
				"Monitoring and Forecasting", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo wpApiInfo() {
		return new ApiInfo("门户网站（wp）", // 标题
				"门户网站资讯版（PI）和数据版（PE）相关表的API文档", // 描述
				"Web Portals", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("api_key", "api_key", "header");
	}

}
