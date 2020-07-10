package com.laboratory.component;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextRun implements CommandLineRunner{

	@Autowired
	public ApplicationContext context;




	@Override
	public void run(String... args) throws Exception {
		System.out.println("<-------------------- BEAN LOADED ------------------->");
		Arrays.asList(context.getBeanDefinitionNames()).stream().forEach(System.out::println);
		System.out.println("<-------------------- BEAN END ------------------->");
//		RequestMappingHandlerAdapter ad = new RequestMappingHandlerAdapter();
		
	}

}
