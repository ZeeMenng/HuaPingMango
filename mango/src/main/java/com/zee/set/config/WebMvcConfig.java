package com.zee.set.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zee.set.interceptor.InterfaceRequestInterceptor;
import com.zee.set.symbolic.CustomSymbolic;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.zee.app.**.gp.**", "com.zee.app.**.pi.**", "com.zee.set.**" })
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WebContent/**").addResourceLocations("forward:/WEB-INF/index.html");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessTokenVerifyInterceptor())

				.addPathPatterns(CustomSymbolic.API_ROOT_URL)

				.excludePathPatterns(CustomSymbolic.AUTHENTICATION_URL);
	}

	@Bean
	public InterfaceRequestInterceptor accessTokenVerifyInterceptor() {
		return new InterfaceRequestInterceptor();
	}

}
