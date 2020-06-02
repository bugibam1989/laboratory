package com.laboratory;


import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.laboratory.mapper", 
			annotationClass = Mapper.class)
public class LaboratoryApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LaboratoryApplication.class);
		app.run(args);
	}

}
