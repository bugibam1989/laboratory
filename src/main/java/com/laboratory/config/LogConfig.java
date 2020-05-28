package com.laboratory.config;

import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class LogConfig extends ConditionEvaluationReportLoggingListener{

	@Override
	public LogLevel getLogLevelForReport() {
		// TODO Auto-generated method stub
		return super.getLogLevelForReport();
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		super.initialize(applicationContext);
	}

	@Override
	protected void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		super.onApplicationEvent(event);
	}

	@Override
	public void logAutoConfigurationReport(boolean isCrashReport) {
		// TODO Auto-generated method stub
		super.logAutoConfigurationReport(isCrashReport);
	}
	
}
