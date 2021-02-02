package com.zee.set.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.zee.set.interceptor.AccessTokenVerifyInterceptor;
import com.zee.set.symbolic.CustomSymbolic;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.zee.app.**.gp.**", "com.zee.app.**.pi.**", "com.zee.set.**"})
public class SpringBootConfig extends WebMvcAutoConfigurationAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WebContent/**").addResourceLocations("forward:/WEB-INF/index.html");
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessTokenVerifyInterceptor())

				.addPathPatterns(CustomSymbolic.API_ROOT_URL)

				.excludePathPatterns(CustomSymbolic.AUTHENTICATION_URL);

		super.addInterceptors(registry);

	}

	@Bean
	public AccessTokenVerifyInterceptor accessTokenVerifyInterceptor() {
		return new AccessTokenVerifyInterceptor();
	}

	/**
	 * 自定义错误页面。
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, CustomSymbolic.ERROR_PAGE));
				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, CustomSymbolic.ERROR_PAGE));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, CustomSymbolic.ERROR_PAGE));
			}
		};
	}

}
