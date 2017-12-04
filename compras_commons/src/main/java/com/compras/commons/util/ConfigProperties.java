package com.compras.commons.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.compras.commons.emuns.ErrorCodeEnum;


@PropertySource(value = "classpath:config-project.properties", ignoreResourceNotFound = true)
@Component
public class ConfigProperties {

	@Autowired 
	Environment environment; 
	
	
	public String getPropiedad(String key) {
		if (environment != null) {
			return environment.getProperty(key);
		}
		return ErrorCodeEnum.GENERAL_ERROR.getCode();
	}
}
