package com.laboratory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry regi) {
		regi.addResourceHandler("/webjars/*").addResourceLocations("classpath:/META-INF/resources/webjars/");
		//regi.addResourceHandler("/css/**").addResourceLocations("/css/");
		//regi.addResourceHandler("/images/**").addResourceLocations("/images/");
		regi.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		WebMvcConfigurer.super.addResourceHandlers(regi);
	}



}
