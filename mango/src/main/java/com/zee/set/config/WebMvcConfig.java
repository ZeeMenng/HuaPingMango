package com.zee.set.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zee.set.interceptor.InterfaceRequestInterceptor;
import com.zee.set.serializer.JacksonNullSerializer;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2021年2月5日 下午3:43:05
 * @updateDate 2021年2月5日 下午3:43:05
 * 
 * @description 升级SpringBoot后，时间转换配置不生效，重写addInterceptors方法以添加接口请求拦截器，用于登录校验 和接口权限校验。
 * 
 * @description 升级SpringBoot后，时间转换配置不生效，重写extendMessageConverters方法以转换时间。
 * 
 * @description Spring自动序列化返回值为JSON字符串，重写configureMessageConverters，如果为属性NULL，则对应成空字符串''，而非之前的'null'字符串。
 * 
 * 
 *              但此配置开启后swagger无法正常使用，暂没有双全的方法。
 * 
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${spring.jackson.date-format}")
	private String dateFormatPattern;

	@Value("${spring.jackson.time-zone}")
	private String timeZone;

	@Resource(name = "interfaceRequestInterceptor")
	InterfaceRequestInterceptor interfaceRequestInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WebContent/**").addResourceLocations("forward:/WEB-INF/index.html");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interfaceRequestInterceptor).addPathPatterns(CustomSymbolic.API_ROOT_URL).excludePathPatterns(CustomSymbolic.AUTHENTICATION_URL);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = converter.getObjectMapper();
		// 生成JSON时,将所有Long转换成String
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
		// 时间格式化
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 这个可以引用spring boot yml 里的格式化配置和时区配置
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormatPattern));
		objectMapper.setTimeZone(TimeZone.getTimeZone(timeZone));
		// 设置格式化内容
		converter.setObjectMapper(objectMapper);
		converters.add(0, converter);

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new CustomObjectMapper());
		return converter;
	}

	public class CustomObjectMapper extends ObjectMapper {
		public CustomObjectMapper() {
			this.getSerializerProvider().setNullValueSerializer(new JacksonNullSerializer());

		}
	}

}
