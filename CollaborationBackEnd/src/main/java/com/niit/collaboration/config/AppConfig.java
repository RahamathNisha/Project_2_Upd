package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//similar to dispatcher servlet.xml
//@Configuration
//

@EnableWebMvc
//@ComponentScan(basePackages = "com.niit.collaboration")
public class AppConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	@Bean
	public ViewResolver viewResolver() {
		logger.debug("Starting of the method viewResolver");

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".html");
		logger.debug("Ending of the method viewResolver");

		return viewResolver;
	}
}
