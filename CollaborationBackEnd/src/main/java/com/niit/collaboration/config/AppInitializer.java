package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml - Java based configuration.

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger = 
			LoggerFactory.getLogger(AppInitializer.class);
	@Override
	protected Class[] getRootConfigClasses() {
		logger.debug("Starting of the metnod getRootConfigClasses");
		return new Class[] { AppConfig.class, WebSocketConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("Starting of the metnod getServletConfigClasses");
		return new Class[] { AppConfig.class, WebSocketConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		logger.debug("Starting of the metnod getServletMappings");
		return new String[] { "/" };
	}
}