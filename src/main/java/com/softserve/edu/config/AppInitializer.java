package com.softserve.edu.config;

import com.softserve.edu.config.WebConfig;

public class AppInitializer {//extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	//@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	//@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	//@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
