package com.laboratory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry regi) {
		//regi.addResourceHandler("/js/*").addResourceLocations("/js/");   -> 왜 안될까?
		regi.addResourceHandler("/webjars/*").addResourceLocations("classpath:/META-INF/resources/webjars/");


		//regi.addResourceHandler("/css/**").addResourceLocations("/css/");

		//regi.addResourceHandler("/images/**").addResourceLocations("/images/");

		WebMvcConfigurer.super.addResourceHandlers(regi);
	}



}
