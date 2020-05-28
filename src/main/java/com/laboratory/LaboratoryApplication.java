package com.laboratory;

import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class LaboratoryApplication {

	public static void main(String[] args) {
		ConditionEvaluationReportLoggingListener listeners = new ConditionEvaluationReportLoggingListener(LogLevel.DEBUG);
		SpringApplication app = new SpringApplication(LaboratoryApplication.class);
		app.run(args);
		//SpringApplication.run(LaboratoryApplication.class, args);
	}

}
