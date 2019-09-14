package com.jusfoun.set.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.set.serializer.JacksonNullSerializer;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:13:26
 * @updateDate 2017年4月13日 下午3:13:26
 * @description 开启此配置，Spring自动序列化返回值为JSON字符串，如果为属性NULL，则对应成空字符串''，而非之前的'null'
 *              字符串。 但此配置开启后swagger无法正常使用，暂没有双全的方法。
 * 
 */
// @Configuration
public class JacksonConfig extends WebMvcConfigurationSupport {
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
		addDefaultHttpMessageConverters(converters);
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