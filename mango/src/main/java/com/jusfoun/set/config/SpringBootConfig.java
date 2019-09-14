package com.jusfoun.set.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.jusfoun.set.interceptor.AccessTokenVerifyInterceptor;
import com.jusfoun.utl.SymbolicConstant;

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringBootConfig extends WebMvcAutoConfigurationAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WebContent/**").addResourceLocations("forward:/WEB-INF/index.html");
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessTokenVerifyInterceptor())

				.addPathPatterns(SymbolicConstant.API_ROOT_URL)

				.excludePathPatterns(SymbolicConstant.AUTHENTICATION_URL);

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
				container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, SymbolicConstant.ERROR_PAGE));
				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, SymbolicConstant.ERROR_PAGE));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, SymbolicConstant.ERROR_PAGE));
			}
		};
	}

}
