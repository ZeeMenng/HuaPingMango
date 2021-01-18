package com.jusfoun.app.start;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.jusfoun.set.config.MybatisConfig;

@Configuration
@Import({ MybatisConfig.class })
@ComponentScan(basePackages = { "com.**.base.**","com.**.custom.**", "com.**.gp.**", "com.**.pi.**", "com.jusfoun.set.**", "com.jusfoun.utl.**" },
excludeFilters = { 
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { 
				com.jusfoun.utl.Executors.class,
				com.jusfoun.utl.MongoUtil.class,
				com.jusfoun.app.custom.MongodbController.class,
				com.jusfoun.utl.service.DaUserContributionUtil.class}),
		@Filter(type = FilterType.REGEX, pattern  = "com.jusfoun.utl.crawler.*"),
		@Filter(type = FilterType.REGEX, pattern  = "com.jusfoun.utl.task.*")
})
//屏蔽MongoDB自动连接
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableScheduling
@EnableCaching
public class Starter extends SpringBootServletInitializer {

	private static Class<Starter> applicationClass = Starter.class;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(Starter.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Bean
	public CharacterEncodingFilter initializeCharacterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	/**  
	 * 文件上传配置  
	 * @return  
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();

		return factory.createMultipartConfig();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	/**
	 * 
	 * @description:注入全局鉴权过滤器
	 * @author: djw
	 * @param:
	 * @return:
	 * @date:2016年6月3日 下午7:02:07
	 */
	// @Bean
	// public FilterRegistrationBean initAuthFilter() {
	// return new FilterRegistrationBean(new AuthenticationFilter());
	// }
}
